package com.company.backend.repository;

import com.company.backend.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry,Long> {
    List<Enquiry> findAllByUserId(Long id);
}
