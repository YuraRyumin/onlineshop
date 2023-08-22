package com.onlineshop.service;

import com.onlineshop.domain.Color;
import com.onlineshop.domain.Company;
import com.onlineshop.domain.Good;
import com.onlineshop.dto.GoodDTO;
import com.onlineshop.repository.ColorRepo;
import com.onlineshop.repository.CompanyRepo;
import com.onlineshop.repository.GoodRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class GoodService {
    private final GoodRepo goodRepo;
    private final ColorRepo colorRepo;
    private final CompanyRepo companyRepo;

    public GoodService(GoodRepo goodRepo, ColorRepo colorRepo, CompanyRepo companyRepo) {
        this.goodRepo = goodRepo;
        this.colorRepo = colorRepo;
        this.companyRepo = companyRepo;
    }

    public GoodDTO getEmptyDTO(){
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setName("");
        goodDTO.setColor("");
        goodDTO.setDescription("");
        goodDTO.setShortDescription("");
        goodDTO.setProducer("");
        goodDTO.setLength(0);
        goodDTO.setHeight(0);
        goodDTO.setWidth(0);
        goodDTO.setWeight(0f);
        goodDTO.setPrice(0f);
        return  goodDTO;
    }

    public GoodDTO convertEntityToDTO(Good good){
        GoodDTO goodDTO = new GoodDTO();
        goodDTO.setId(good.getId());
        goodDTO.setName(good.getName());
        if(good.getColor() == null){
            goodDTO.setColor("");
        } else {
            goodDTO.setColor(good.getColor().getName());
        }
        goodDTO.setDescription(good.getDescription());
        goodDTO.setShortDescription(good.getShortDescription());
        if(good.getProducer() == null){
            goodDTO.setProducer("");
        } else {
            goodDTO.setProducer(good.getProducer().getName());
        }
        goodDTO.setLength(good.getLength());
        goodDTO.setHeight(good.getHeight());
        goodDTO.setWidth(good.getWidth());
        goodDTO.setWeight(good.getWeight());
        goodDTO.setPrice(good.getPrice());
        return  goodDTO;
    }

    public Iterable<GoodDTO> convertAllEntityToDTO(Iterable<Good> goods){
        return StreamSupport.stream(goods.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public Iterable<GoodDTO> getAllGoods(){
        return convertAllEntityToDTO(goodRepo.findAll());
    }

    public GoodDTO getGoodByUuid(String uuidGood){
        if(uuidGood.isEmpty()){
            return getEmptyDTO();
        }
        return convertEntityToDTO(goodRepo.findFirstByUuid(uuidGood));
    }

    public Iterable<GoodDTO> getAllGoodsForPage(Integer pageNumber){
        return null;//convertAllEntityToDTO(goodRepo.findTopBy(pageNumber));
    }

    @Transactional
    public void saveGood(Good good){
        goodRepo.save(good);
    }

    @Transactional
    public void saveGood(String uuid, String name,
                         String producerName, String colorName,
                         String description, String shortDescription,
                         Integer height, Integer width,
                         Integer length, Float weight,
                         Float price){
        Good good = new Good();
        good.setUuid(uuid);
        good.setName(name);
        Color color = colorRepo.findFirstByName(colorName);
        if(color != null){
            good.setColor(color);
        }
        Company company = companyRepo.findFirstByName(producerName);
        if(company != null){
            good.setProducer(company);
        }
        good.setDescription(description);
        good.setShortDescription(shortDescription);
        good.setHeight(height);
        good.setWidth(width);
        good.setLength(length);
        good.setPrice(price);
        goodRepo.save(good);
    }
}
