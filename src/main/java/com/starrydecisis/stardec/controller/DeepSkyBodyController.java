package com.starrydecisis.stardec.controller;

import com.starrydecisis.stardec.model.DeepSkyBody;
import com.starrydecisis.stardec.repository.BodySearchRepository;
import com.starrydecisis.stardec.repository.DeepSkyBodyRepository;
import com.starrydecisis.stardec.service.DeepSkyBodyService;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/DeepSkyBody")
public class DeepSkyBodyController {

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyController.class);

    private final DeepSkyBodyService deepSkyBodyService;
    private final DeepSkyBodyRepository deepSkyBodyRepository;
    private final BodySearchRepository bodySearchRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private final ElasticsearchOperations elasticsearchRestTemplate;

    @Autowired
    public DeepSkyBodyController(DeepSkyBodyService deepSkyBodyService,
                                 BodySearchRepository bodySearchRepository,
                                 DeepSkyBodyRepository deepSkyBodyRepository,
                                 ElasticsearchOperations elasticsearchTemplate
    ) {
        this.deepSkyBodyService = deepSkyBodyService;
        this.deepSkyBodyRepository = deepSkyBodyRepository;
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

    @GetMapping(path = "{bodyId}")
    public DeepSkyBody getDeepSkyBody(@PathVariable("bodyId") Long bodyId) {
        Optional<DeepSkyBody> optionalBody = deepSkyBodyService.getDeepSkyBody(bodyId);
        if (optionalBody.isEmpty()) {
            throw new IllegalStateException("missing body getDeepSkyBody");
        } else {
            return optionalBody.get();
        }
    }

    @GetMapping("/constellation")
    public List<DeepSkyBody> getDeepSkyBodiesByConstellation(
            @RequestParam("constellationName") String constellation) {
        return deepSkyBodyService.getDeepSkyBodyConstellation(constellation);
    }

    @GetMapping("/bodyType")
    public List<DeepSkyBody> getDeepSkyBodiesByBodyType(
            @RequestParam("bodyType") String bodyType) {
        return deepSkyBodyService.getDeepSkyBodyBodyType(bodyType);
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
    @GetMapping("/reindexAll")
    public void reindexAllBodies() {
        // Newly created bodies are indexed in real time during creation, but this will reindex all the data from postgres
        // TODO - Refactor to use Logstash for reindexing instead of manually reindexing within spring boot
        List<DeepSkyBody> allBodies = deepSkyBodyRepository.findAll();
        bodySearchRepository.saveAll(allBodies);
    }


//     working
//    @GetMapping("/getAllSearch")
//    public Page<DeepSkyBody> dumbSearchDeepSkyBody() {
//        return bodySearchRepository.findAll(PageRequest.of(0, 10));  // works
//    }

    // working
    @GetMapping("/getBody")   // redundant? JPA repo already has a search by body Name
    public Page<DeepSkyBody> getBodySearch(@RequestParam(name = "bodyName", required = true) final String bodyName) {
        return bodySearchRepository.findByBodyName(bodyName, PageRequest.of(0, 10));  // works
    }

    // working
//    @GetMapping("/dumbSearch")
//    public Page<DeepSkyBody> dumbSearchDeepSkyBody(@RequestParam(name = "searchPhrase", required = true) final String searchPhrase) {
//        return bodySearchRepository.dumbSearchDeepSkyBody(searchPhrase, PageRequest.of(0, 10));
//    }

//    @GetMapping("/smartSearch")
//    public List<DeepSkyBody> mainSearchDeepSkyBody(
//            @RequestParam(name = "searchPhrase", required = true) final String searchPhrase) {
//
//        // The main search query for DeepSkyBodies is going to have to lower the relevance of the description field.
//        // The description field mentions neighboring stars/galaxies by name. I need to boost id and lower description
//        return deepSkyBodyService.mainSearchDeepSkyBody(searchPhrase);
//    }

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

