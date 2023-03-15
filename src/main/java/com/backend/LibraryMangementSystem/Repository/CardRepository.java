package com.backend.LibraryMangementSystem.Repository;

import com.backend.LibraryMangementSystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
