package com.company.backend.controller;

import com.company.backend.entity.Product;
import com.company.backend.entity.Shop;
import com.company.backend.service.ProductService;
import com.company.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final ShopService shopService;
    private final ProductService productService;

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        System.out.println("🚀 User profile endpoint hit!");
        return ResponseEntity.ok("User profile data");
    }

    @GetMapping("/getShopDetails/{id}")
    public Shop getShopDetailsById(@PathVariable Long id)
    {
        return shopService.getShopDetailsById(id);
    }

    @GetMapping("/shopProducts/{id}")
    public List<Product> getShopProductsByShopId(@PathVariable Long id)
    {
        System.out.println(id);
        return productService.getShopProductsByShopId(id);
    }
}
