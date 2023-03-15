package com.backend.LibraryMangementSystem.Service;

import com.backend.LibraryMangementSystem.DTO.BookRequestDTO;
import com.backend.LibraryMangementSystem.DTO.BookResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Author;
import com.backend.LibraryMangementSystem.Entity.Book;
import com.backend.LibraryMangementSystem.Repository.AuthorRepository;
import com.backend.LibraryMangementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws Exception {

        Author author;
        try{
            author = authorRepository.findById(bookRequestDTO.getAuthorId()).get();
        }catch(Exception e){
            throw new Exception("AuthorId is invalid");
        }

        //update all book details
        Book book = new Book();

        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPrice(bookRequestDTO.getPrice());
        book.setIssued(false);
        //set author to book
        book.setAuthor(author);

        //add this book to authors list of books
        author.getBooks().add(book);
        authorRepository.save(author);

        //make a response DTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setTitle(book.getTitle());
        return bookResponseDTO;
    }
}
