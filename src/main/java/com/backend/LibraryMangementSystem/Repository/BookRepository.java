package com.backend.LibraryMangementSystem.Repository;

import com.backend.LibraryMangementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
