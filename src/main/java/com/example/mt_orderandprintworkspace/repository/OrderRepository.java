package com.example.mt_orderandprintworkspace.repository;

import com.example.mt_orderandprintworkspace.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
