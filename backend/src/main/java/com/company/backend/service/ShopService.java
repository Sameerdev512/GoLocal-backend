package com.company.backend.service;

import com.company.backend.dto.ShopRequest;
import com.company.backend.entity.Role;
import com.company.backend.entity.Shop;
import com.company.backend.entity.User;
import com.company.backend.repository.ShopRepository;
import com.company.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class ShopService {
    final ShopRepository shopRepository;
    final UserRepository userRepository;

    public ShopService(ShopRepository shopRepository,UserRepository userRepository)
    {
        this.shopRepository= shopRepository;
        this.userRepository= userRepository;
    }

    @PostMapping("/shopRegistration")
    public ResponseEntity<String> registerShop(@RequestBody ShopRequest shopRequest)
    {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in user

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user already has a shop
        if (user.getShop() != null) {
            return ResponseEntity.badRequest().body("You have already registered a shop.");
        }
        Shop shop = Shop.builder()
                .shopName(shopRequest.getShopName())
                .ownerName(shopRequest.getShopName())
                .address(shopRequest.getAddress())
                .country(shopRequest.getCountry())
                .state(shopRequest.getState())
                .city(shopRequest.getCity())
                .contact(shopRequest.getContact())
                .shopCategory(shopRequest.getShopCategory())
                .isApproved(false)
                .user(user)
                .build();

                shopRepository.save(shop);

        //change the role from USER to SELLER
        user.setRole(Role.SELLER);
        userRepository.save(user);

        return ResponseEntity.ok("Shop registered successfully");
    }
}
