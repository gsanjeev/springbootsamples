package com.laxtech.example.repository;

import com.laxtech.example.model.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long> {
}
