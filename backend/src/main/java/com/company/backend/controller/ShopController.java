package com.company.backend.controller;

import com.company.backend.dto.ShopRequest;
import com.company.backend.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ShopController {

    final ShopService shopService;

    public ShopController(ShopService shopService)
    {
        this.shopService = shopService;
    }

    @PostMapping("/registershop")
    public ResponseEntity<String> registerShop(@Valid @RequestBody ShopRequest shopRequest)
    {
       return  shopService.registerShop(shopRequest);
    }
}
