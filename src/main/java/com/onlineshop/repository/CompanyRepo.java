package com.onlineshop.repository;

import com.onlineshop.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    Company findFirstByName(String name);
    Company findFirstById(Long id);
}
