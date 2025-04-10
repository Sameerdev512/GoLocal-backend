package com.company.backend.controller;

import com.company.backend.entity.Product;
import com.company.backend.repository.ProductRepository;
import com.company.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;

    @GetMapping("/profile")
    public String getSellerDashboard() {
        return "Seller profile data";
    }

}
