package com.example.laxtech.products;

import java.io.Serializable;

public class ProductLine  implements Serializable {

    String name;

    public ProductLine() {   }

    public ProductLine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
