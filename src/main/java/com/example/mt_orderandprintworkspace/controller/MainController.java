package com.example.mt_orderandprintworkspace.controller;

import com.example.mt_orderandprintworkspace.service.ProductsCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private final ProductsCatalogService productsCatalogService;

    @Autowired
    public MainController(ProductsCatalogService productsCatalogService) {
        this.productsCatalogService = productsCatalogService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("productsCatalogActualDate", productsCatalogService.getActualDateForShow());
        return "main";
    }
}
