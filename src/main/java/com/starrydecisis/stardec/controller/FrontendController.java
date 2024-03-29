package com.starrydecisis.stardec.controller;


import com.starrydecisis.stardec.model.DeepSkyBody;
import com.starrydecisis.stardec.model.immutableLookupTables.BodyName;
import com.starrydecisis.stardec.model.immutableLookupTables.BodyType;
import com.starrydecisis.stardec.model.immutableLookupTables.Constellation;
import com.starrydecisis.stardec.model.immutableLookupTables.Description;
import com.starrydecisis.stardec.service.DeepSkyBodyService;
import com.starrydecisis.stardec.service.immutableServices.BodyNameService;
import com.starrydecisis.stardec.service.immutableServices.BodyTypeService;
import com.starrydecisis.stardec.service.immutableServices.ConstellationService;
import com.starrydecisis.stardec.service.immutableServices.DescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class FrontendController {
    // THIS IS A "BACKEND" project.
    // THE FRONTEND IS ADDED FOR OBSERVERS WHO DON'T WANT TO READ THE API CONTRACT.

    private static final Logger logger = LoggerFactory.getLogger(DeepSkyBodyController.class);

    // TODO - Collapse the smaller repository and services into a single service???
    // TODO - I think it is best practice to have one repo per table though... conflicting best practices
    @Autowired
    private DeepSkyBodyService deepSkyBodyService;
    @Autowired
    private ConstellationService constellationService;
    @Autowired
    private BodyNameService bodyNameService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private DescriptionService descriptionService;
    // Home Page
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "bodyName", "asc", model);
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about.html";
    }

    @GetMapping("/constellations")
    public String constellations(Model model) {
        List<Constellation> allConstellationsList = constellationService.getAllConstellations();
        model.addAttribute("allConstellations", allConstellationsList);
        return "constellations";
    }

    @GetMapping("/bodyNames")
    public String bodyNames(Model model) {
        List<BodyName> allBodyNames = bodyNameService.getAllBodyNames();
        model.addAttribute("allBodyNames", allBodyNames);
        return "body_names";
    }

    @GetMapping("/bodyTypes")
    public String bodyTypes(Model model) {
        List<BodyType> allBodyTypes = bodyTypeService.getAllBodyTypes();
        model.addAttribute("allBodyTypes", allBodyTypes);
        return "body_types";
    }

    @GetMapping("/descriptions")
    public String descriptions(Model model) {
        List<Description> allDescriptions = descriptionService.getAllDescriptions();
        model.addAttribute("allDescriptions", allDescriptions);
        return "descriptions";
    }

    @GetMapping("/newBody")
    public String newBody(Model model) {
        // create model attribute to bind form data
        DeepSkyBody deepSkyBody = new DeepSkyBody();
        model.addAttribute("deepSkyBody", deepSkyBody);
        return "new_body";
    }

    @PostMapping("/addNewDeepSkyBody")
    public String addNewDeepSkyBody(@ModelAttribute("deepSkyBody") DeepSkyBody deepSkyBody) {
        DeepSkyBody bodyCreated = deepSkyBodyService.addNewBody(deepSkyBody);
        return "redirect:/updateBody/" + deepSkyBody.getId();
    }

    @PostMapping("/updateExistingDeepSkyBody")
    public String updateExistingDeepSkyBody(@ModelAttribute("deepSkyBody") DeepSkyBody deepSkyBody) {
        DeepSkyBody updatedBody = deepSkyBodyService.updateDeepSkyBody(deepSkyBody);
        return "redirect:/updateBody/" + updatedBody.getId();
    }

    @GetMapping("/updateBody/{id}")
    public String updateBody(@PathVariable( value = "id") long id, Model model) {
        DeepSkyBody deepSkyBody = deepSkyBodyService.getDeepSkyBody(id).orElseThrow();

        // set deepSkyBody as a model attribute to pre-populate the form
        model.addAttribute("deepSkyBody", deepSkyBody);
        return "update_body";
    }

    @GetMapping("/deleteDeepSkyBody/{id}")
    public String deleteDeepSkyBody(@PathVariable (value = "id") long id) {
        deepSkyBodyService.deleteDeepSkyBody(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;

        Page<DeepSkyBody> page = deepSkyBodyService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<DeepSkyBody> bodyList = page.getContent();

//        model.addAttribute("currentPage", pageNo);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentPageNumber", page.getNumber() + 1);
        model.addAttribute("totalPages", page.getTotalPages());

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("bodyList", bodyList);
        return "index";
    }

    @GetMapping("/smartSearch")
    public String smartSearchPostMapping(@RequestParam("searchPhrase") String searchPhrase, Model model) {
        logger.info("searchPhrase = " + searchPhrase);
        List<DeepSkyBody> searchResults = deepSkyBodyService.mainSearchDeepSkyBody(searchPhrase);
        logger.info("searchResults for smartSearch has numberOfResults = " + searchResults.size());

        model.addAttribute("bodyList", searchResults);
        model.addAttribute("bodyListSize", searchResults.size());
        return "body_search_results";
    }
}
