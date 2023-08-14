package com.onlineshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "good")
    private Good good;

    @Column(name = "quantity")
    private Integer quantity;

    public Bucket() {
    }

    public Bucket(User user, Good good, Integer quantity) {
        this.user = user;
        this.good = good;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
