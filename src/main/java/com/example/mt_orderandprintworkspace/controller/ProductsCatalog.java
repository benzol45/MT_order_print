package com.example.mt_orderandprintworkspace.controller;

import com.example.mt_orderandprintworkspace.service.ProductsCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/productsCatalog")
public class ProductsCatalog {
    private final ProductsCatalogService productsCatalogService;

    @Autowired
    public ProductsCatalog(ProductsCatalogService productsCatalogService) {
        this.productsCatalogService = productsCatalogService;
    }

    @GetMapping("/Actualize")
    public String actualizeCatalog(Model model) {
        Map<String,Object> result = productsCatalogService.actualize();
        if (result.get("done").equals(true)) {
            model.addAttribute("done",true);
            model.addAttribute("updatedProducts", result.get("updatedProducts"));
            model.addAttribute("addedProducts", result.get("addedProducts"));
            //TODO красиво табличкой вывести новые и измененные товары
        } else {
            model.addAttribute("done",false);
            model.addAttribute("error", result.get("error"));
        }
        return "actualizeCatalog";
    }
}
