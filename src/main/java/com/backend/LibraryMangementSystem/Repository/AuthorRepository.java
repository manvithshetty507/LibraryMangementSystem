package com.backend.LibraryMangementSystem.Repository;

import com.backend.LibraryMangementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
