package com.onlineshop.dto;

import lombok.Data;

@Data
public class BucketDTO {
    private Long id;
    private String user;
    private String good;
    private Integer quantity;
}
