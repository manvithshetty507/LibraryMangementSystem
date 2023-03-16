package com.backend.LibraryMangementSystem.Controller;

import com.backend.LibraryMangementSystem.DTO.IssueBookRequestDTO;
import com.backend.LibraryMangementSystem.DTO.IssueBookResponseDTO;
import com.backend.LibraryMangementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
        IssueBookResponseDTO issueBookResponseDTO;
        try{
            issueBookResponseDTO = transactionService.issueBook(issueBookRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage()+" :Hence Failed!",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(issueBookResponseDTO, HttpStatus.ACCEPTED);
    }
}
