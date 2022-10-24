package com.example.mt_orderandprintworkspace.repository;

import com.example.mt_orderandprintworkspace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByGTIN(String gtin);
}
