package com.laxtech.example.repository;

import com.laxtech.example.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
