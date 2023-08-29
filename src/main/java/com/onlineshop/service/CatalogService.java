package com.onlineshop.service;

import com.onlineshop.domain.Catalog;
import com.onlineshop.dto.CatalogDTO;
import com.onlineshop.repository.CatalogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
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

    @Transactional
    public void saveCatalog(Catalog catalog){
        Catalog thisCatalog = catalogRepo.findFirstByUuid(catalog.getUuid());
        if(thisCatalog != null){
            thisCatalog = new Catalog();
        }
        thisCatalog.setName(catalog.getName());
        thisCatalog.setParent(catalog.getParent());
        catalogRepo.save(thisCatalog);
    }

    @Transactional
    public void saveCatalog(String uuidParent, String uuid, String name){
        Catalog catalog = new Catalog();
        Catalog parent = catalogRepo.findFirstByUuid(uuidParent);
        if(parent != null){
            catalog.setParent(parent);
        }
        if(uuid.isEmpty()){
            catalog.setUuid(UUID.randomUUID().toString());
        } else {
            catalog.setUuid(uuid);
        }
        catalog.setName(name);
        catalogRepo.save(catalog);
    }

    public Iterable<CatalogDTO> getAllCatalogs(){
        return convertAllEntityToDTO(catalogRepo.findAll());
    }
}
