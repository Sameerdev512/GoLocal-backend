package com.company.backend.controller;

import com.company.backend.entity.Enquiry;
import com.company.backend.entity.Product;
import com.company.backend.entity.Shop;
import com.company.backend.service.EnquiryService;
import com.company.backend.service.ProductService;
import com.company.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final ShopService shopService;
    private final ProductService productService;
    private final EnquiryService enquiryService;

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

    @GetMapping("/getProductDetails/{id}")
    public Product getShopProductsDetailsById(@PathVariable Long id)
    {
        System.out.println(id);
        return productService.getShopProductsDetailsById(id);
    }

    @PostMapping("/submit-enquiry")
    public ResponseEntity<Map<String,String>> addEnquiry(@RequestBody Enquiry enquiry)
    {
        return enquiryService.submitEnquiry(enquiry);
    }

    @GetMapping("/get-users-enquiries")
    public List<Enquiry> getUsersEnquiries()
    {
        return enquiryService.getUsersEnquiries();
    }
}
