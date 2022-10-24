package com.example.mt_orderandprintworkspace.gateway;

import com.example.mt_orderandprintworkspace.entity.Order;
import com.example.mt_orderandprintworkspace.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Gateway {
    public List<Product> getProductsList() {
        //TODO MOCK !!! Должен быть запрос к внешнему REST

        Product product0 = new Product();
        product0.setName("Test_Product");
        product0.setGTIN("1");

        Product product1 = new Product();
        product1.setName("TestProduct_" + Math.random());
        product1.setGTIN(Double.toString(Math.random()));

        Product product2 = new Product();
        product2.setName("TestProduct_" + Math.random());
        product2.setGTIN(Double.toString(Math.random()));

        return List.of(product0,product1,product2);
    }

    public void sendOrder(Order order) {
        //TODO отправляем заказ на обработку криптошлюзу
    }
}
