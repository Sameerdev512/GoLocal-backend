package com.company.backend.repository;

import com.company.backend.entity.Shop;
import com.company.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByShopName(String shopName);
    Optional<Shop> findByUser(User user);

    Shop findByUserId(Long id);

    Shop findShopById(Long id);
}
