package com.company.backend.controller;

import com.company.backend.dto.ProductRequest;
import com.company.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/seller/")
@CrossOrigin(origins="http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @PostMapping("/shop/addproduct")
    public ResponseEntity<Map<String, String>> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("imageUrl") MultipartFile imageUrl
    ) throws IOException {
        return productService.addProduct(name, description, price, stock, imageUrl);
    }

}
