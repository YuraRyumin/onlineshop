package com.onlineshop.service;

import com.onlineshop.domain.Catalog;
import com.onlineshop.dto.CatalogDTO;
import com.onlineshop.repository.CatalogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class CatalogService {
    private final CatalogRepo catalogRepo;

    public CatalogService(CatalogRepo catalogRepo) {
        this.catalogRepo = catalogRepo;
    }

    public CatalogDTO getEmptyDTO(){
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setUuid("");
        catalogDTO.setName("");
        return catalogDTO;
    }

    public CatalogDTO convertEntityToDTO(Catalog catalog){
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setId(catalog.getId());
        catalogDTO.setUuid(catalog.getUuid());
        catalogDTO.setName(catalog.getName());
        if(catalog.getParent() != null) {
            catalogDTO.setParent(catalog.getParent().getUuid());
        }
        return catalogDTO;
    }

    public Iterable<CatalogDTO> convertAllEntityToDTO(Iterable<Catalog> catalogs){
        return StreamSupport.stream(catalogs.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}
