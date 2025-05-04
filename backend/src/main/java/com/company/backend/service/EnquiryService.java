package com.company.backend.service;

import com.company.backend.entity.Enquiry;
import com.company.backend.entity.User;
import com.company.backend.repository.EnquiryRepository;
import com.company.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;


    private final UserRepository userRepository;



    public ResponseEntity<Map<String, String>> submitEnquiry(Enquiry enquiry) {
        // Get the currently logged-in seller
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enquiry enquiry1 = Enquiry.builder()
                .userId(user.getId())
                .userName(enquiry.getUserName())
                .contactNo(enquiry.getContactNo())
                .message(enquiry.getMessage())
                .email(enquiry.getEmail())
                .status("pending")
                .createdAt(LocalDateTime.now())
                .productId(enquiry.getProductId())
                .productName(enquiry.getProductName())
                .build();

        enquiryRepository.save(enquiry1);

        Map<String,String> response = new HashMap<>();
        response.put("message","enquiry submitted successfully");

        return ResponseEntity.ok(response);

    }

    public List<Enquiry> getUsersEnquiries() {
        // Get the currently logged-in seller
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return enquiryRepository.findAllByUserId(user.getId());
    }
}
