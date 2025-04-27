package com.company.backend.repository;

import com.company.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByNameAndShopId(String name, Long id);

    Product getProductById(Long productId);

    List<Product> findAllByShopId(Long id);
}
