package com.laxtech.example.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name="address_id")
    @RestResource(path="libraryAddress", rel="address")
    private Address address;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    public Library() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", books=" + books +
                '}';
    }
}
