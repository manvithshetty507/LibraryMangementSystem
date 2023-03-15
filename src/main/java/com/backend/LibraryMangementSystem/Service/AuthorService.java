package com.backend.LibraryMangementSystem.Service;

import com.backend.LibraryMangementSystem.DTO.AuthorAddRequestDTO;
import com.backend.LibraryMangementSystem.DTO.AuthorAddResponseDTO;
import com.backend.LibraryMangementSystem.Entity.Author;
import com.backend.LibraryMangementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public AuthorAddResponseDTO add(AuthorAddRequestDTO authorAddRequestDTO) {
        Author author = new Author();
        //transfer data from request to author object
        author.setAge(authorAddRequestDTO.getAge());
        author.setEmail(authorAddRequestDTO.getEmail());
        author.setName(authorAddRequestDTO.getName());
        author.setMobNo(authorAddRequestDTO.getMobNo());

        //save
        authorRepository.save(author);
        //make a response DTO
        AuthorAddResponseDTO authorAddResponseDTO = new AuthorAddResponseDTO();
        authorAddResponseDTO.setAuthorId(author.getId());
        authorAddResponseDTO.setName(author.getName());
        return authorAddResponseDTO;
    }

    public List<Author> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }
}
