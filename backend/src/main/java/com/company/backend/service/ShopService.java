package com.company.backend.service;

import com.company.backend.dto.ShopRequest;
import com.company.backend.entity.Role;
import com.company.backend.entity.Shop;
import com.company.backend.entity.User;
import com.company.backend.repository.ShopRepository;
import com.company.backend.repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    final ShopRepository shopRepository;
    final UserRepository userRepository;

    public ShopService(ShopRepository shopRepository,UserRepository userRepository)
    {
        this.shopRepository= shopRepository;
        this.userRepository= userRepository;
    }

    public ResponseEntity<String> registerShop(@RequestBody ShopRequest shopRequest)
    {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in user

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user already has a shop
        if (user.getShop() != null) {
            return ResponseEntity.ok("You have already registered a shop.");
        }

        if(shopRepository.findByShopName(shopRequest.getShopName()).isPresent())
        {
            return ResponseEntity.ok("Shop already exists with same name");
        }
        Shop shop = Shop.builder()
                .shopName(shopRequest.getShopName())
                .ownerName(shopRequest.getOwnerName())
                .address(shopRequest.getAddress())
                .country(shopRequest.getCountry())
                .state(shopRequest.getState())
                .city(shopRequest.getCity())
                .contact(shopRequest.getContact())
                .shopCategory(shopRequest.getShopCategory())
                .isApproved(false)
                .description(shopRequest.getDescription())
                .gstin(shopRequest.getGstin())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .email(user.getUsername())
                .user(user)
                .build();

                shopRepository.save(shop);

        //change the role from USER to SELLER
        user.setRole(Role.SELLER);
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println(user.getUpdatedAt());
        userRepository.save(user);
        System.out.println(user.getUpdatedAt());

        return ResponseEntity.ok("Shop registered successfully");
    }

    public Shop getShopDetails() {
        //get logged in user
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in user

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return shopRepository.findByUserId(user.getId());
    }

    public ResponseEntity<Map<String, String>> updateShopDetails(ShopRequest shopRequest,Long id) {
        Shop shop = shopRepository.findShopById(id);

        shop.setOpeningTime(shopRequest.getOpeningTime());
        shop.setClosingTime(shopRequest.getClosingTime());
        shop.setContact(shop.getContact());
        shop.setAddress(shopRequest.getAddress());
        shop.setCountry(shopRequest.getCountry());
        shop.setDescription(shopRequest.getDescription());
        shop.setCity(shopRequest.getCity());

        shopRepository.save(shop);

        Map<String,String> response = new HashMap<>();
        response.put("message","Shop details updated successfully");

        return ResponseEntity.ok(response);
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopDetailsById(Long id) {
        return shopRepository.findShopById(id);
    }
}
