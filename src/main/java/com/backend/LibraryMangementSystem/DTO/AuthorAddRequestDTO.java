package com.backend.LibraryMangementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorAddRequestDTO {
    private String name;
    private int age;
    private String mobNo;
    private String email;
}
