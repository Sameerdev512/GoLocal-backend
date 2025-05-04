package com.company.backend.controller;

import com.company.backend.dto.ShopRequest;
import com.company.backend.entity.Shop;
import com.company.backend.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = "/registershop")
    public ResponseEntity<String> registerShop(
            @RequestParam("shopName") String shopName,
            @RequestParam("ownerName") String ownerName,
            @RequestParam("address") String address,
            @RequestParam("country") String country,
            @RequestParam("state") String state,
            @RequestParam("city") String city,
            @RequestParam("contact") String contact,
            @RequestParam("shopCategory") String shopCategory,
            @RequestParam("description") String description,
            @RequestParam("gstin") String gstin,
            @RequestParam("imageUrl") MultipartFile imageUrl
    ) throws IOException {

        ShopRequest shopRequest = new ShopRequest();
        shopRequest.setShopName(shopName);
        shopRequest.setOwnerName(ownerName);
        shopRequest.setAddress(address);
        shopRequest.setCountry(country);
        shopRequest.setState(state);
        shopRequest.setCity(city);
        shopRequest.setContact(contact);
        shopRequest.setShopCategory(shopCategory);
        shopRequest.setDescription(description);
        shopRequest.setGstin(gstin);
        shopRequest.setImageUrl(imageUrl); // or handle the image separately if needed

        return shopService.registerShop(shopRequest);
    }


    @GetMapping("/getAllShops")
    public List<Shop> getAllShops()
    {
        return shopService.getAllShops();
    }


}
