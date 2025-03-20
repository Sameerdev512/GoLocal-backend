package com.company.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shopName;
    private String ownerName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String contact;
    private String shopCategory;
    private boolean isApproved = false;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user; // Each user can register only one shop

}
