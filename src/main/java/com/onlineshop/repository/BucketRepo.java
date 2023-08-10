package com.onlineshop.repository;

import com.onlineshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<BucketRepo, Long> {
    Bucket[] findAllById(Long id);
    Bucket[] findAllByUserUuid(String uuid);
}
