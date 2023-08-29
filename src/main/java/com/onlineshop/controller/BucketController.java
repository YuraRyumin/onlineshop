package com.onlineshop.controller;

import com.onlineshop.domain.Bucket;
import com.onlineshop.dto.BucketDTO;
import com.onlineshop.service.BucketService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/bucket")
    public Iterable<BucketDTO> listOfBuckets(@RequestParam String uuidUser){
        return bucketService.getBucketByUserUuid(uuidUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createbucket")
    public void createBucket(@RequestBody Bucket bucket){
        bucketService.saveBucket(bucket);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savebucket")
    public void saveBucket(@RequestBody Bucket bucket){
        bucketService.saveBucket(bucket);
    }
}
