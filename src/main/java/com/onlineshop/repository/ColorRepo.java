package com.onlineshop.repository;

import com.onlineshop.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepo extends JpaRepository<Color, Long> {
    Color findFirstById(Long id);
    Color findFirstByName(String name);
}
