package com.example.laxtech.actors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Actor {

    Long id;

    String name;

    int age;

    @ApiModelProperty(position = 1, required = true, value = "0")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(position = 2, required = true, value = "Sanjeev")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(position = 3, required = true, value = "30")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
