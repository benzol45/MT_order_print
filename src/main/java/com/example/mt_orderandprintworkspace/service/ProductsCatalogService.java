package com.example.mt_orderandprintworkspace.service;

import com.example.mt_orderandprintworkspace.entity.Product;
import com.example.mt_orderandprintworkspace.gateway.Gateway;
import com.example.mt_orderandprintworkspace.repository.ProductRepository;
import com.sun.source.tree.OpensTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductsCatalogService {
    private final ProductRepository productRepository;
    private final CatalogStateService catalogStateService;
    private final Gateway gateway;

    @Autowired
    public ProductsCatalogService(ProductRepository productRepository, CatalogStateService catalogStateService, Gateway gateway) {
        this.productRepository = productRepository;
        this.catalogStateService = catalogStateService;
        this.gateway = gateway;
    }

    public LocalDateTime getActualDate() {
        return catalogStateService.getCatalogActualizeDate();
    }

    public String getActualDateForShow() {
        LocalDateTime dateTime = getActualDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(dateTimeFormatter);
    }


    public Map<String, Object> actualize() {
        List<Product> productList = gateway.getProductsList();
        if (productList==null) {
            throw new IllegalStateException("Can't get products list from gateway");
        }

        catalogStateService.setCatalogActualizeDateNow();

        List<Product> updatedProducts = new ArrayList<>();
        List<Product> addedProducts = new ArrayList<>();
        for (Product product: productList) {
            Optional<Product> productInDatabase = productRepository.findByGTIN(product.getGTIN());
            if (productInDatabase.isPresent()) {
                product.setId(productInDatabase.get().getId());
                if (!product.equals(productInDatabase.get())) {
                    productRepository.save(product);
                    updatedProducts.add(product);
                }
            } else {
                product.setId(0);
                productRepository.save(product);
                addedProducts.add(product);
            }
        }

        Map<String,Object> result = Map.of(
                "done",true,
                "updatedProducts", updatedProducts,
                "addedProducts", addedProducts);

        return result;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }
}
