package com.Ecom.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    private String category;
    private double price;
    private String description;
    private String imageUrl;
    public Product(){

    }
    public Product(String name, String category, double price, String description, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl= imageUrl;
    }
}
