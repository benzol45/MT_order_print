package com.example.mt_orderandprintworkspace.service;

import com.example.mt_orderandprintworkspace.entity.Order;
import com.example.mt_orderandprintworkspace.entity.Product;
import com.example.mt_orderandprintworkspace.entity.enums.OrderState;
import com.example.mt_orderandprintworkspace.gateway.Gateway;
import com.example.mt_orderandprintworkspace.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductsCatalogService productsCatalogService;
    private final Gateway gateway;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductsCatalogService productsCatalogService, Gateway gateway) {
        this.orderRepository = orderRepository;
        this.productsCatalogService = productsCatalogService;
        this.gateway = gateway;
    }

    public void addNewOrder(long productId, int quantity) {
        Optional<Product> optionalProduct = productsCatalogService.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new IllegalArgumentException("Incorrect product id in order");
        }

        if (quantity<=0) {
            throw new IllegalArgumentException("Incorrect quantity of product in order");
        }

        Order order = new Order();
        order.setCreateDate(new Date());
        order.setProduct(optionalProduct.get());
        order.setQuantity(quantity);
        order.setState(OrderState.NEW);

        orderRepository.save(order);

        gateway.sendOrder(order);
    }

    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        orders.sort(Comparator.comparing(Order::getCreateDate).reversed());
        return orders;
    }
}
