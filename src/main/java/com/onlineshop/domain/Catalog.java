package com.onlineshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "parent")
    private Catalog parent;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    public Catalog() {
    }

    public Catalog(Catalog parent, String uuid, String name) {
        this.parent = parent;
        this.uuid = uuid;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
