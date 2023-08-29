package com.onlineshop.service;

import com.onlineshop.domain.Company;
import com.onlineshop.dto.CompanyDTO;
import com.onlineshop.repository.CompanyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public CompanyDTO getEmptyDTO(){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("");
        companyDTO.setAddress("");
        return  companyDTO;
    }

    public CompanyDTO convertEntityToDTO(Company company){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setAddress(company.getAddress());
        return companyDTO;
    }

    public Iterable<CompanyDTO> converAllEntityToDTO(Iterable<Company> companies){
        return StreamSupport.stream(companies.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public CompanyDTO getCompanyByName(String name){
        if(name.isEmpty()){
            return getEmptyDTO();
        }
        return convertEntityToDTO(companyRepo.findFirstByName(name));
    }

    public Iterable<CompanyDTO> getAllCompanies(){
        return converAllEntityToDTO(companyRepo.findAll());
    }

    @Transactional
    public void saveCompany(Company company){
        Company thisCompany = companyRepo.findFirstByName(company.getName());
        if(thisCompany == null){
            thisCompany = new Company();
        }
        thisCompany.setAddress(company.getAddress());
        companyRepo.save(thisCompany);
    }

    @Transactional
    public void saveCompany(String name, String address){
        Company company = new Company(name, address);
        companyRepo.save(company);
    }
}
