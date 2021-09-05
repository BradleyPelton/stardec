package com.starrydecisis.stardec.deepskybody;

import com.starrydecisis.stardec.bodysearch.BodySearchRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.DistanceFeatureQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RankFeatureQueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/DeepSkyBody")
public class DeepSkyBodyController {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyController.class);

    private final DeepSkyBodyService deepSkyBodyService;
    private final BodySearchRepository bodySearchRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private final ElasticsearchOperations elasticsearchRestTemplate;

//    @Autowired
//    private ElasticsearchOperations elasticsearchTemplate;

    @Autowired
    public DeepSkyBodyController(DeepSkyBodyService deepSkyBodyService,
                                 BodySearchRepository bodySearchRepository,
                                 ElasticsearchOperations elasticsearchTemplate
    ) {
        this.deepSkyBodyService = deepSkyBodyService;
        this.bodySearchRepository = bodySearchRepository;
        this.elasticsearchRestTemplate = elasticsearchTemplate;
    }


    ///////////////////////////////////////
    ///// SPRING WEB BASIC CRUD API ENDPOINTS
    ///////////////////////////////////////
    @GetMapping
    public List<DeepSkyBody> getDeepSkyBodies() {
        return deepSkyBodyService.getDeepSkyBodies();
    }

    @PostMapping
    public void createNewDeepSkyBody(@RequestBody DeepSkyBody deepSkyBody) {
        deepSkyBodyService.addNewBody(deepSkyBody);
    }

    @DeleteMapping(path = "{bodyId}")
    public void deleteDeepSkyBody(@PathVariable("bodyId") Long bodyId) {
        // assert not null
        deepSkyBodyService.deleteDeepSkyBody(bodyId);
    }

    @PutMapping
    public void updateDeepSkyBody(@RequestBody DeepSkyBody deepSkyBody) {
        deepSkyBodyService.updateDeepSkyBody(deepSkyBody);
    }

    ///////////////////////////////////////
    ///// ElasticSearch endpoints
    ///////////////////////////////////////
    // working
    @GetMapping("/getAllSearch")
    public Page<DeepSkyBody> dumbSearchDeepSkyBody() {
        return bodySearchRepository.findAll(PageRequest.of(0, 10));  // works
    }

    // working
    @GetMapping("/getBody")   // redundant? JPA repo already has a search by body Name
    public Page<DeepSkyBody> getBodySearch(@RequestParam(name = "bodyName", required = true) final String bodyName) {
        return bodySearchRepository.findByBodyName(bodyName, PageRequest.of(0, 10));  // works
    }

    @GetMapping("/dumbSearch")
    public Page<DeepSkyBody> dumbSearchDeepSkyBody(@RequestParam(name = "searchPhrase", required = true) final String searchPhrase) {
        return bodySearchRepository.dumbSearchDeepSkyBody(searchPhrase, PageRequest.of(0, 10));
    }

    @GetMapping("/smartSearch")
    public SearchHits<DeepSkyBody> mainSearchDeepSkyBody(@RequestParam(name = "searchPhrase", required = true) final String searchPhrase) {
        // There are many ways to build queries in the spring data elasticsearch library.
        // The main search query for DeepSkyBodies is going to have to lower the relevance of the description field.
        // The description fields mention neighboring stars/galaxies by name. I need to boost id and lower description
//        GET /_search
//        {
//            "query": {
//            "constant_score": {
//                "filter": {
//                    "term": { “type”: "nginx" }
//                },
//                "boost": 1.5
//            }
//        }
//        }


//        //
        final BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("approved", true))
                .should(QueryBuilders.distanceFeatureQuery("created_at", new DistanceFeatureQueryBuilder.Origin("now"), "7d"))
                .should(RankFeatureQueryBuilders.saturation("votes.rank"));

        Query searchQuery = new NativeSearchQuery(queryBuilder);


        SearchHits<DeepSkyBody> bodies = elasticsearchRestTemplate
                .search(searchQuery, DeepSkyBody.class);

        return bodies;

    }

    // Manually use a "search" function? Seems excessive. maybe for projects with hundreds of unique queries it's handy.
//    private List<DeepSkyBody> search(Query query) {
//        final SearchHits<DeepSkyBody> result = elasticsearchTemplate.search(query, DeepSkyBody.class);
//        if (result.isEmpty()) {
//            return Collections.emptyList();
//        }
//        final List<DeepSkyBody> links = result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
//        return links;
//    }


}
