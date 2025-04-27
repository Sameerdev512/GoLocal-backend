package com.company.backend.controller;

import com.company.backend.dto.ShopRequest;
import com.company.backend.entity.Shop;
import com.company.backend.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="http://localhost:5173")
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

    @GetMapping("/getAllShops")
    public List<Shop> getAllShops()
    {
        return shopService.getAllShops();
    }


}
