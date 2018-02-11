package com.example.laxtech.products;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private String title;
    private String description;
    private String image;
    private ProductLine productLine;



    public Product(String id, String title, String description, String image, ProductLine productLine) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.productLine = productLine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
