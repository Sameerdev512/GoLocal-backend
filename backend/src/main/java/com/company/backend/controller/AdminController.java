package com.company.backend.controller;

import com.company.backend.entity.Product;
import com.company.backend.entity.Shop;
import com.company.backend.entity.User;
import com.company.backend.repository.ProductRepository;
import com.company.backend.repository.ShopRepository;
import com.company.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    final private UserRepository userRepository;
    final private ProductRepository productRepository;
    final private ShopRepository shopRepository;

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Admin dashboard data";
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/get-all-products")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @GetMapping("/get-all-shops")
    public List<Shop> getAllShops()
    {
        return shopRepository.findAll();
    }
}
