package com.example.laxtech.keyvaluestore;

import java.io.Serializable;

public class Actor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    public Actor(String id, String name){
        this.id=id;
        this.name=name;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Actor{" + "id=" +id + '\''  + ", name =" + name + "}";
    }

}
