package com.starrydecisis.stardec.deepskybody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/DeepSkyBody")
public class DeepSkyBodyController {

    private final DeepSkyBodyService deepSkyBodyService;

    @Autowired
    public DeepSkyBodyController(DeepSkyBodyService deepSkyBodyService) {
        this.deepSkyBodyService = deepSkyBodyService;
    }


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

}
