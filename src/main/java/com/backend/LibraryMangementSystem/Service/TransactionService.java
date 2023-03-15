package com.backend.LibraryMangementSystem.Service;

import com.backend.LibraryMangementSystem.DTO.BookResponseDTO;
import com.backend.LibraryMangementSystem.DTO.IssueBookRequestDTO;
import com.backend.LibraryMangementSystem.DTO.IssueBookResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Book;
import com.backend.LibraryMangementSystem.Entity.Transaction;
import com.backend.LibraryMangementSystem.Enum.TransactionStatus;
import com.backend.LibraryMangementSystem.Repository.BookRepository;
import com.backend.LibraryMangementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) {
        //transaction object
        Transaction transaction = new Transaction();

        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIsIssuedOperation(true);

        //4 conditions
        //1:
        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid BookId");
            transactionRepository.save(transaction);
            throw new RuntimeException("BookId is inValid");
        }
        return  null;
    }
}
