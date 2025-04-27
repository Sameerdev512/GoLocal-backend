package com.company.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//when registering for shop user will become seller
@Getter
public class ShopRequest {
    @NotBlank(message="shop name should not blank.")
    private String shopName;
    @NotBlank(message="owner name should not blank.")
    private String ownerName;
    @NotBlank(message="address should not blank.")
    private String address;
    @NotBlank(message="country should not blank.")
    private String country;
    @NotBlank(message="state should not blank.")
    private String state;
    @NotBlank(message="city should not blank.")
    private String city;
    @NotBlank(message="contact should not blank.")
    private String contact;
    @NotBlank(message="shop category should not blank.")
    private String shopCategory;
    private String description;
    private String gstin;

    private String openingTime;
    private String closingTime;

}
