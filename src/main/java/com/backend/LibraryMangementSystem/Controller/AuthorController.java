package com.backend.LibraryMangementSystem.Controller;

import com.backend.LibraryMangementSystem.DTO.AuthorAddRequestDTO;
import com.backend.LibraryMangementSystem.DTO.AuthorAddResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Author;
import com.backend.LibraryMangementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public AuthorAddResponseDTO addAuthor(@RequestBody AuthorAddRequestDTO authorAddRequestDTO){

        AuthorAddResponseDTO authorAddResponseDTO = authorService.add(authorAddRequestDTO);
        return authorAddResponseDTO;
    }

    @GetMapping("/getAuthors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }
}
