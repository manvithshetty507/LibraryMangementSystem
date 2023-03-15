package com.backend.LibraryMangementSystem.Repository;

import com.backend.LibraryMangementSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
