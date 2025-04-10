package com.company.backend.service;

import com.company.backend.entity.Product;
import com.company.backend.entity.Shop;
import com.company.backend.entity.User;
import com.company.backend.repository.ProductRepository;
import com.company.backend.repository.ShopRepository;
import com.company.backend.repository.UserRepository;
import com.company.backend.util.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final CloudinaryService cloudinaryService;

    ProductService(ProductRepository productRepository,ShopRepository shopRepository,UserRepository userRepository,CloudinaryService cloudinaryService)
    {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.userRepository  = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public ResponseEntity<Map<String,String>> addProduct(String name,
                                                         String description,
                                                         double price,
                                                         int stock,
                                                         MultipartFile imageUrl) throws IOException {
        // Get the currently logged-in seller
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User seller = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        // Find the shop registered by this seller
        Shop shop = shopRepository.findByUser(seller)
                .orElseThrow(() -> new RuntimeException("Shop not found for this seller"));

        //return error if seller add product with same name that is in database
        if (productRepository.existsByNameAndSellerId(name, seller.getId())) {
            Map<String,String> response = new HashMap<>();
            response.put("error","error");
            response.put("message","Product exists with the same name.");
            return ResponseEntity.ok(response);
        }

        String imageFile = cloudinaryService.uploadFile(imageUrl); // Upload to Cloudinary

        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .imageUrl(imageFile)
                .status(stock > 0 ? "Available" : "Not Available")
                .seller(seller)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRepository.save(product);
        Map<String,String> response = new HashMap<>();
        response.put("ok","ok");
        response.put("message","Product added successfully.");
        return ResponseEntity.ok(response);
    }

}
