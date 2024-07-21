package com.example.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private String district;
    private String address;
    private Double area;
    private Integer rooms;
    private Double price;

}
