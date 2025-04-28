package com.company.backend.controller;

import com.company.backend.entity.Product;
import com.company.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:5173")

public class ProductController {

    ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    private final ProductService productService;

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId)
    {
        return productService.getProductById(productId);
    }


}
