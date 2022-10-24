package com.example.mt_orderandprintworkspace.controller;

import com.example.mt_orderandprintworkspace.service.OrderService;
import com.example.mt_orderandprintworkspace.service.ProductsCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/markCode/Order")
public class OrderController {
    private final OrderService orderService;
    private final ProductsCatalogService productsCatalogService;

    @Autowired
    public OrderController(OrderService orderService, ProductsCatalogService productsCatalogService) {
        this.orderService = orderService;
        this.productsCatalogService = productsCatalogService;
    }

    @GetMapping()
    public String getAllOrdersPage(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders";
    }

    @GetMapping("/new")
    public String getNewOrderPage(Model model) {
        model.addAttribute("products", productsCatalogService.getAll());
        return "new_order";
    }

    @PostMapping("/new")
    public String createNewOrder(@RequestParam("product_id")long productId, @RequestParam("quantity")int quantity) {
        orderService.addNewOrder(productId, quantity);
        return "redirect:/markCode/Order";
    }
}
