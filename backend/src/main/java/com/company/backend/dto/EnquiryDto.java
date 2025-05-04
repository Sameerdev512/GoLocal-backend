package com.company.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDto {
    private String userName;
    private String email;
    private String contactNo;
    private String message;
    private String productName;
    private String productId;
    private String response;
    private String status;
    private Long shopId;
    private LocalDateTime createdAt;
    private LocalDateTime respondedAt;
}
