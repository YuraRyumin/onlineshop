package com.onlineshop.repository;

import com.onlineshop.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepo extends JpaRepository<Good, Long> {
    Good findFirstByUuid(String uuid);
    //Iterable<Good> findTopBy(Integer pageNumber);

}
