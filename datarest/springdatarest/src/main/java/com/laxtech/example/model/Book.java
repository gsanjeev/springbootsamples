package com.laxtech.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", library=" + library +
                ", authors=" + authors +
                '}';
    }
}
