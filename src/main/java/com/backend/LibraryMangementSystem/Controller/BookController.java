package com.backend.LibraryMangementSystem.Controller;

import com.backend.LibraryMangementSystem.DTO.BookRequestDTO;
import com.backend.LibraryMangementSystem.DTO.BookResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Book;
import com.backend.LibraryMangementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody BookRequestDTO bookRequestDTO) throws Exception {

        BookResponseDTO bookResponseDTO;
        try{
            bookResponseDTO = bookService.addBook(bookRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(bookResponseDTO, HttpStatus.ACCEPTED);
    }
}
