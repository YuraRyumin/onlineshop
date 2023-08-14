package com.onlineshop.repository;

import com.onlineshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket, Long> {
    Iterable<Bucket> findAllById(Long id);
    Iterable<Bucket> findAllByUserUuid(String uuid);
}
