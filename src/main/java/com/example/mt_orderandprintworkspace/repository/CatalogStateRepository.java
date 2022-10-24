package com.example.mt_orderandprintworkspace.repository;

import com.example.mt_orderandprintworkspace.entity.CatalogState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogStateRepository extends JpaRepository<CatalogState, Integer> {
}
