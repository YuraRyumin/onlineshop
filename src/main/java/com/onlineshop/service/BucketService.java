package com.onlineshop.service;

import com.onlineshop.domain.Bucket;
import com.onlineshop.domain.Good;
import com.onlineshop.domain.User;
import com.onlineshop.dto.BucketDTO;
import com.onlineshop.repository.BucketRepo;
import com.onlineshop.repository.GoodRepo;
import com.onlineshop.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class BucketService {
    private final BucketRepo bucketRepo;
    private final UserRepo userRepo;
    private final GoodRepo goodRepo;

    public BucketService(BucketRepo bucketRepo, UserRepo userRepo, GoodRepo goodRepo) {
        this.bucketRepo = bucketRepo;
        this.userRepo = userRepo;
        this.goodRepo = goodRepo;
    }

    public BucketDTO getEmptyDTO(){
        BucketDTO bucketDTO = new BucketDTO();
        bucketDTO.setUser("");
        bucketDTO.setGood("");
        bucketDTO.setQuantity(0);
        return bucketDTO;
    }

    public BucketDTO convertEntityToDTO(Bucket bucket){
        BucketDTO bucketDTO = new BucketDTO();
        bucketDTO.setId(bucket.getId());
        bucketDTO.setQuantity(bucket.getQuantity());
        if(bucket.getGood() != null){
            bucketDTO.setGood(bucket.getGood().getName());
        }
        if(bucket.getUser() != null){
            bucketDTO.setUser(bucket.getUser().getLogin());
        }
        return bucketDTO;
    }

    public Iterable<BucketDTO> convertAllEntityToDTO(Iterable<Bucket> buckets){
        return StreamSupport.stream(buckets.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public Iterable<BucketDTO> getBucketByUserUuid(String uuidUser){
        if(uuidUser.isEmpty()){
            List<BucketDTO> bucketDTOList = new ArrayList<>();
            bucketDTOList.add(getEmptyDTO());
            return bucketDTOList;
        }
        return convertAllEntityToDTO(bucketRepo.findAllByUserUuid(uuidUser));
    }

    @Transactional
    public void saveBucket(Bucket bucket){
        bucketRepo.save(bucket);
    }

    @Transactional
    public void saveBucket(String uuidUser, String uuidGood, Integer quantity){
        Bucket bucket = new Bucket();
        if(!uuidUser.isEmpty()){
            User user = userRepo.findFirstByUuid(uuidUser);
            if(user != null) {
                bucket.setUser(user);
            }
        }
        if(!uuidGood.isEmpty()){
            Good good = goodRepo.findFirstByUuid(uuidGood);
            if(good != null) {
                bucket.setGood(good);
            }
        }
        bucket.setQuantity(quantity);
        bucketRepo.save(bucket);
    }
}
