package com.starrydecisis.stardec.controller;


import com.starrydecisis.stardec.model.DeepSkyBody;
import com.starrydecisis.stardec.service.DeepSkyBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FrontendController {
    // THIS IS A "BACKEND" project.
    // THE FRONTEND IS ADDED FOR OBSERVERS WHO DON'T WANT TO READ THE API CONTRACT.
    @Autowired
    private DeepSkyBodyService deepSkyBodyService;

    // Home Page
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "bodyName", "asc", model);
    }

    @GetMapping("/showNewDeepSkyBodyForm")
    public String showNewDeepSkyBodyForm(Model model) {
        // create model attribute to bind form data
        DeepSkyBody deepSkyBody = new DeepSkyBody();
        model.addAttribute("deepSkyBody", deepSkyBody);
        return "new_body";
    }

    @PostMapping("/saveDeepSkyBody")
    public String saveDeepSkyBody(@ModelAttribute("deepSkyBody") DeepSkyBody deepSkyBody) {
        // save employee to database
        deepSkyBodyService.saveDeepSkyBody(deepSkyBody);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        DeepSkyBody deepSkyBody = deepSkyBodyService.getDeepSkyBodyById(id);

        // set deepSkyBody as a model attribute to pre-populate the form
        model.addAttribute("deepSkyBody", deepSkyBody);
        return "update_body";
    }

    @GetMapping("/deleteDeepSkyBody/{id}")
    public String deleteDeepSkyBody(@PathVariable (value = "id") long id) {
        deepSkyBodyService.deleteDeepSkyBodyById(id);
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

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("bodyList", bodyList);
        return "index";
    }

    @GetMapping("/smartSearch/{id}")
    public String deleteDeepSkyBody(@RequestParam("searchPhrase") String searchPhrase,
                                            Model model) {
        deepSkyBodyService.deleteDeepSkyBodyById(id);
        return "body_search_results";
    }
}
