package com.company.backend.controller;

import com.company.backend.dto.EnquiryDto;
import com.company.backend.dto.ShopRequest;
import com.company.backend.entity.Enquiry;
import com.company.backend.entity.Product;
import com.company.backend.entity.Shop;
import com.company.backend.repository.ProductRepository;
import com.company.backend.repository.ShopRepository;
import com.company.backend.service.EnquiryService;
import com.company.backend.service.ProductService;
import com.company.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;
    private final ShopService shopService;
    private final EnquiryService enquiryService;

    @GetMapping("/profile")
    public String getSellerDashboard() {
        return "Seller profile data";
    }

    @PostMapping("/addproduct")
    public ResponseEntity<Map<String,String>> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("category") String category,
            @RequestParam("imageUrl") MultipartFile imageUrl
    ) throws IOException {
        return productService.addProduct(name,description,price,stock,category,imageUrl);
    }

    @PutMapping("/updateproduct/{productId}")
    public ResponseEntity<Map<String,String>> updateProduct(
            @PathVariable Long productId,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("category") String category,
            @RequestParam("status") String status
    ) throws IOException {
        return productService.updateProduct(productId,description,price,stock,category,status);
    }

    @DeleteMapping("/products/{productId}")
    void deleteById(@PathVariable Long productId)
    {
        productService.deleteByProductId(productId);
    }

    @GetMapping("/findAllProducts")
    private List<Product> findAllProductsByShopId()
    {
        return productService.findAllByShopId();
    }

    @GetMapping("/getShopDetails")
    public Shop getShopDetails()
    {
        return shopService.getShopDetails();
    }

    @PutMapping("/updateShopDetails/{id}")
    public ResponseEntity<Map<String,String>> updateShopDetails(@RequestBody ShopRequest shopRequest,@PathVariable Long id)
    {
        return shopService.updateShopDetails(shopRequest,id);
    }

    @GetMapping("/get-all-shop-enquiries")
    public List<Enquiry> getAllSellerEnquiries()
    {
        return enquiryService.getAllSellerEnquiries();
    }

    @PutMapping("/respond-to-enquiry/{id}")
    public ResponseEntity<Map<String,String>> respondEnquiry(@RequestBody EnquiryDto enquiryDto, @PathVariable Long id)
    {
        return enquiryService.respondEnquiry(id,enquiryDto);
    }
}
