package com.onlineshop.dto;

import lombok.Data;

@Data
public class GoodDTO {
    private Long id;
    private String name;
    private String producer;
    private String color;
    private String description;
    private String shortDescription;
    private Integer height;
    private Integer width;
    private Integer length;
    private Float weight;
    private Float price;
}
