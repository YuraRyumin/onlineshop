package com.onlineshop.controller;

import com.onlineshop.domain.Company;
import com.onlineshop.dto.CompanyDTO;
import com.onlineshop.service.CompanyService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companieslist")
    public Iterable<CompanyDTO> listOfCompanies(){
        return companyService.getAllCompanies();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createcompany")
    public void createCatalog(@RequestBody Company company){
        companyService.saveCompany(company);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savecompany")
    public void saveCatalog(@RequestBody Company company){
        companyService.saveCompany(company);
    }
}
