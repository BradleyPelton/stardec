package com.starrydecisis.stardec.service;

import com.starrydecisis.stardec.model.DeepSkyBody;
import com.starrydecisis.stardec.repository.BodySearchRepository;
import com.starrydecisis.stardec.repository.DeepSkyBodyRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeepSkyBodyService {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyService.class);

    private final DeepSkyBodyRepository deepSkyBodyRepository;
    private final BodySearchRepository bodySearchRepository;

    @Autowired
    private RestHighLevelClient client;

    private final ElasticsearchOperations elasticsearchRestTemplate;

    @Autowired
    public DeepSkyBodyService(DeepSkyBodyRepository deepSkyBodyRepository,
                              BodySearchRepository bodySearchRepository,
                              ElasticsearchOperations elasticsearchRestTemplate) {
        this.deepSkyBodyRepository = deepSkyBodyRepository;
        this.bodySearchRepository = bodySearchRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<DeepSkyBody> getDeepSkyBodies() {
        return deepSkyBodyRepository.findAll();
    }

    public Optional<DeepSkyBody> getDeepSkyBody(Long bodyId) {
        boolean exists = deepSkyBodyRepository.existsById(bodyId);
        if (!exists) {
            logger.error("getDeepSkyBody invalid id, id = " + bodyId);
            throw new IllegalArgumentException("getDeepSkyBody invalid id, id = " + bodyId); // TODO - I
        } else {
            return deepSkyBodyRepository.findById(bodyId);
        }
    }

    public Optional<DeepSkyBody> getDeepSkyBodyByName(String bodyName) {
        return deepSkyBodyRepository.findDeepSkyBodyByBodyName(bodyName);
    }

    public List<DeepSkyBody> getDeepSkyBodyConstellation(String constellation) {
        return deepSkyBodyRepository.findDeepSkyBodiesByConstellation(constellation);
    }

    public List<DeepSkyBody> getDeepSkyBodyBodyType(String bodyType) {
        return deepSkyBodyRepository.findDeepSkyBodiesByBodyType(bodyType);
    }

    public DeepSkyBody addNewBody(DeepSkyBody newBody){
        Optional<DeepSkyBody> bodyOptional =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(newBody.getBodyName());

        if (bodyOptional.isPresent()) {
            throw new IllegalArgumentException("body name already taken"); // TODO - Create custom exception
        }
        deepSkyBodyRepository.save(newBody);
        logger.info("deepSkyBody bodyName=" + newBody.getBodyName() + " created and persisted");

        return bodySearchRepository.save(newBody);
    }

    public void deleteDeepSkyBody(Long bodyId) {
        boolean exists = deepSkyBodyRepository.existsById(bodyId);
        if (!exists) {
            throw new IllegalStateException("cannot delete body with bodyId = " + bodyId + " , body id does not exist");
        } else {
            DeepSkyBody body = deepSkyBodyRepository.getById(bodyId);
            deepSkyBodyRepository.delete(body);
            bodySearchRepository.deleteById(bodyId);  // .delete() doesn't work, but .deleteById() does. Hmmmmmm
        }
    }

    @Transactional
    public DeepSkyBody updateDeepSkyBody(DeepSkyBody apiBody) {
        Optional<DeepSkyBody> existingBody =
                deepSkyBodyRepository.findDeepSkyBodyByBodyName(apiBody.getBodyName());

        if (existingBody.isEmpty()) {
            throw new IllegalStateException("Cannot update body with bodyName = " + apiBody.getBodyName() + " , bodyName does not exist");
        } else {
            deepSkyBodyRepository.updateDeepSkyBodyByBodyName( // TODO - BUG. Update is creating a second document.
                    existingBody.get().getId(),
                    apiBody.getBodyName(),
                    apiBody.getOtherName(),
                    apiBody.getBodyType(),
                    apiBody.getConstellation(),
                    apiBody.getDescription(),
                    apiBody.getNotes()
            );
            return bodySearchRepository.save(existingBody.get());
        }
    }

    public List<DeepSkyBody> mainSearchDeepSkyBody(
            @RequestParam(name = "searchPhrase", required = true) final String searchPhrase) {
        logger.debug("searchPhrase inside mainSearch = " + searchPhrase);
        // The main search query for DeepSkyBodies is going to have to lower the relevance of the description field.
        // The description field mentions neighboring stars/galaxies by name. I need to boost id and lower description

        // PRIORITIZE NAME SEARCHES TO THE bodyName FIELD
//        GET /deepskybodyindex/_search
//        {"query": {
//            "boosting": {
//                "positive": {"match":{"bodyName":"?0"}},
//                "negative": {"match":{"otherName":"?0"}},
//                "negative_boost":0.2
//            }
//        }
//        }





//        final QueryBuilder positiveQuery = QueryBuilders.matchQuery("body_name", searchPhrase);
        final QueryBuilder positiveQuery = QueryBuilders.multiMatchQuery(searchPhrase, "body_name", "other_name", "body_type", "constellation","description", "notes");
        final QueryBuilder negativeQuery = QueryBuilders.matchQuery("notes", searchPhrase);
        BoostingQueryBuilder boostingQuery = new BoostingQueryBuilder(positiveQuery, negativeQuery);
        boostingQuery.negativeBoost(0.4f);
        boostingQuery.boost(0.2f);

        Query searchQuery = new NativeSearchQuery(boostingQuery);
        SearchHits<DeepSkyBody> bodies = elasticsearchRestTemplate.search(searchQuery, DeepSkyBody.class);


        final List<DeepSkyBody> bodiesList = bodies.getSearchHits().stream().map(SearchHit::getContent).limit(10).collect(Collectors.toList());

        return bodiesList;





        // FALLBACK CATCHALL TEST QUERY
//        Query searchQuery = new NativeSearchQueryBuilder()
//                .withFilter(regexpQuery("body_name", ".*.*"))
//                .build();
//        SearchHits<DeepSkyBody> results =
//                elasticsearchRestTemplate.search(searchQuery, DeepSkyBody.class, IndexCoordinates.of("deepskybodyindex"));
//
//        final List<DeepSkyBody> bodiesList = results.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());

//        return bodiesList;
    }






    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////     THYMLELEAF STUFF BELOW    //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public Page<DeepSkyBody> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return deepSkyBodyRepository.findAll(pageable);
    }
}
