package com.onlineshop.dto;

import lombok.Data;

@Data
public class CatalogDTO {
    private Long id;
    private String parent;
    private String uuid;
    private String name;
}
