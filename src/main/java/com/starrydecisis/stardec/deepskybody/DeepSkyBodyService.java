package com.starrydecisis.stardec.deepskybody;

import com.starrydecisis.stardec.bodysearch.BodySearchRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeepSkyBodyService {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyService.class);

    private final DeepSkyBodyRepository deepSkyBodyRepository;
    private final BodySearchRepository bodySearchRepository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    public DeepSkyBodyService(DeepSkyBodyRepository deepSkyBodyRepository, BodySearchRepository bodySearchRepository) {
        this.deepSkyBodyRepository = deepSkyBodyRepository;
        this.bodySearchRepository = bodySearchRepository;
    }

    public List<DeepSkyBody> getDeepSkyBodies() {
//        Long id = 0L;  // auto increment?
//        LocalDate nowDate = LocalDate.now();
        return deepSkyBodyRepository.findAll();
    }

    public void addNewBody(DeepSkyBody newBody){
        Optional<DeepSkyBody> bodyOptional =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(newBody.getBodyName());

        if (bodyOptional.isPresent()) {
            throw new IllegalStateException("body name already taken"); // TODO - Create custom exception
        }
        deepSkyBodyRepository.save(newBody);
        logger.info("deepSkyBody bodyName=" + newBody.getBodyName() + " created and persisted");

        // AUTOMATING INDEXING
        bodySearchRepository.save(newBody);
        logger.info("deepSkyBody bodyName=" + newBody.getBodyName() + " indexed into Elasticsearch");


        // MANUAL INDEXING
//        try {
////            final CreateIndexRequest createIndexRequest = new CreateIndexRequest("deepskybodyindex");
////        createIndexRequest.alias(new Alias("links").writeIndex(true));
////            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
////            client.index(createIndexRequest, RequestOptions.DEFAULT)
//
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("feature", "high-level-rest-client");
//            jsonMap.put("user", "kimchy");
//            jsonMap.put("postDate", new Date());
//            jsonMap.put("message", "trying out Elasticsearch");
//
//            IndexRequest request = new IndexRequest("spring-data")   // shardId
////                    .id(String.valueOf(UUID.randomUUID()))           // id for index
//                    .id(newBody.getId().toString())           // id for index
//                    .source(jsonMap)                                //
//                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
//
//            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
////            System.out.println(response.status());
////            System.out.println(response.toString());
//        } catch (IOException e) {
//            System.out.println("IO EXCEPTION DURING addNewBody");
//            e.printStackTrace();
//        }
    }

    public void deleteDeepSkyBody(Long bodyId) {
        boolean exists = deepSkyBodyRepository.existsById(bodyId);
        if (!exists) {
            throw new IllegalStateException("cannot delete body with bodyId = " + bodyId + " , body id does not exist");
        } else {
            DeepSkyBody body = deepSkyBodyRepository.getById(bodyId);
            deepSkyBodyRepository.delete(body);
            bodySearchRepository.delete(body);  // UNTESTED, should flow through
        }
    }

    @Transactional
    public void updateDeepSkyBody(DeepSkyBody apiBody) {
        Optional<DeepSkyBody> bodyOptional =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(apiBody.getBodyName());

        if (!bodyOptional.isPresent()) {
            throw new IllegalStateException("cannot update body with bodyId = " + apiBody.getId() + " , body id does not exist");
        } else {
            DeepSkyBody databaseBody = bodyOptional.get();
            databaseBody.setBodyName(apiBody.getBodyName());
            databaseBody.setBodyType(apiBody.getBodyType());

            bodySearchRepository.save(databaseBody);  // UNTESTED, should flow through
        }
    }
}
