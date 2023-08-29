package com.onlineshop.controller;

import com.onlineshop.domain.Catalog;
import com.onlineshop.dto.CatalogDTO;
import com.onlineshop.service.CatalogService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalogslist")
    public Iterable<CatalogDTO> listOfCatalogs(){
        return catalogService.getAllCatalogs();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createcatalog")
    public void createCatalog(@RequestBody Catalog catalog){
        catalogService.saveCatalog(catalog);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savecatalog")
    public void saveCatalog(@RequestBody Catalog catalog){
        catalogService.saveCatalog(catalog);
    }
}
