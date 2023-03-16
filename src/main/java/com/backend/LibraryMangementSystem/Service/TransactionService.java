package com.backend.LibraryMangementSystem.Service;

import com.backend.LibraryMangementSystem.DTO.BookResponseDTO;
import com.backend.LibraryMangementSystem.DTO.IssueBookRequestDTO;
import com.backend.LibraryMangementSystem.DTO.IssueBookResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Book;
import com.backend.LibraryMangementSystem.Entity.LibraryCard;
import com.backend.LibraryMangementSystem.Entity.Transaction;
import com.backend.LibraryMangementSystem.Enum.CardStatus;
import com.backend.LibraryMangementSystem.Enum.TransactionStatus;
import com.backend.LibraryMangementSystem.Repository.BookRepository;
import com.backend.LibraryMangementSystem.Repository.CardRepository;
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
    @Autowired
    CardRepository cardRepository;

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) {
        //transaction object
        Transaction transaction = new Transaction();

        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIsIssuedOperation(true);

        //4 conditions
        //1: check if book id is valid
        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid BookId");
            transactionRepository.save(transaction);
            throw new RuntimeException("BookId is inValid");
        }
        //2:check if card id is valid
        LibraryCard card;
        try{
            card = cardRepository.findById(issueBookRequestDTO.getCardId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid CardId");
            transactionRepository.save(transaction);
            throw new RuntimeException("CardId is invalid");
        }

        //3:if card is not activated
        if(card.getStatus() != CardStatus.ACTIVE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Your Card is not Activated!");
            transactionRepository.save(transaction);
            throw new RuntimeException("your card is not activated!");
        }
        //4:if book is already issued
        if(book.isIssued() == true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Sorry! Book is already issued.");
            transactionRepository.save(transaction);
            throw new RuntimeException("your card is not activated!");
        }

        //now the book can be issued
        transaction.setBook(book);
        transaction.setLibraryCard(card);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Successful Transaction");
        transactionRepository.save(transaction);

        book.setIssued(true);
        book.setLibrary_Card(card);
        //transaction is a child of book add the transaction to list of it for a book
        book.getTransactionList().add(transaction);
        //transaction is a child of card add the transaction to list of it for a card
        card.getTransactions().add(transaction);
        card.getBookList().add(book);

        //make a responseDTO
        IssueBookResponseDTO issueBookResponseDTO = new IssueBookResponseDTO();
        //set all values
        issueBookResponseDTO.setTransanctionId(transaction.getTransactionNumber());
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setTransactionStatus(TransactionStatus.SUCCESS);

        return  issueBookResponseDTO;
    }
}
