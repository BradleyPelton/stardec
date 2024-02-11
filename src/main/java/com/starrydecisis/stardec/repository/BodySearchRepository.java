package com.starrydecisis.stardec.repository;

import com.starrydecisis.stardec.model.DeepSkyBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySearchRepository extends ElasticsearchRepository<DeepSkyBody, Long> {

    Page<DeepSkyBody> findByBodyName(String bodyName, Pageable pageable);


//    Page<Article> findByAuthorsName(String name, Pageable pageable);

//    @Query("{\"multi_match\": {\"query\": \"?0\",\"fields\": []}}")
//    Page<DeepSkyBody> dumbSearchDeepSkyBody(String searchPhrase, Pageable pageable);
    // Search all fields for any possible match (hence dumb)
//    GET /deepskybodyindex/_search
//    {
//        "query": {
//        "multi_match": {
//            "query": "bob",
//                "fields": []
//        }
//    }
//    }
}
