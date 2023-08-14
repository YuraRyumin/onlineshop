package com.onlineshop.repository;

import com.onlineshop.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    Catalog findFirstByUuid(String uuidCatalog);
}
