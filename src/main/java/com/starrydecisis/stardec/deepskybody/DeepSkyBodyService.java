package com.starrydecisis.stardec.deepskybody;

import com.starrydecisis.stardec.bodysearch.BodySearchRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DeepSkyBodyService {

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
//            bodySearchRepository.save(newBody);

        try {
//            final CreateIndexRequest createIndexRequest = new CreateIndexRequest("deepskybodyindex");
//        createIndexRequest.alias(new Alias("links").writeIndex(true));
//            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//            client.index(createIndexRequest, RequestOptions.DEFAULT)

            IndexRequest request = new IndexRequest("spring-data")
                    .id(newBody.getId().toString())
//                    .source(singletonMap("feature", "high-level-rest-client"))
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
            System.out.println(response.status());
            System.out.println(response.toString());
        } catch (IOException e) {
            System.out.println("IO EXCEPTION DURING addNewBody");
            e.printStackTrace();
        }
    }

    public void deleteDeepSkyBody(Long bodyId) {
        boolean exists = deepSkyBodyRepository.existsById(bodyId);
        if (!exists) {
            throw new IllegalStateException("cannot delete body with bodyId = " + bodyId + " , body id does not exist");
        } else {
            deepSkyBodyRepository.deleteById(bodyId);
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
        }
    }
}
