package com.company.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long userId;
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
