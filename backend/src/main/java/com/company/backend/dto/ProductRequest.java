package com.company.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Getter
public class ProductRequest {
    @NotBlank(message = "name should not blank")
    private String name;
    @NotBlank(message = "description should not blank")
    private String description;
    @NotBlank(message = "price should not blank")
    private double price;
    @NotBlank(message = "quantity should not blank")
    private int stock;
    @NotBlank(message = "image should not blank")


    private String imageUrl;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
