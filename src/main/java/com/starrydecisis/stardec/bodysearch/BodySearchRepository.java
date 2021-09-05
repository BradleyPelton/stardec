package com.starrydecisis.stardec.bodysearch;

import com.starrydecisis.stardec.deepskybody.DeepSkyBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySearchRepository extends ElasticsearchRepository<DeepSkyBody, Long> {

    Page<DeepSkyBody> findByBodyName(String bodyName, Pageable pageable);


//    Page<Article> findByAuthorsName(String name, Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    @Query("{\"query\": { \"multi_match\" : {\"query\" : \"?0\"}}}")
    @Query("{\"query\": { \"multi_match\" : {}")
    Page<DeepSkyBody> dumbSearchDeepSkyBody(String searchPhrase, Pageable pageable);
    // TODO - Buggy, not working
    // Search all fields
    // "_all" method is deprecated in DSL. Use multi_match
    // If no fields are provided, the multi_match query defaults to the index.query.default_field index settings
//        GET /deepskybodyindex/_search
//        {"query": { "multi_match" : {"query" : "dumb search for everything"}}}


}
