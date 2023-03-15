package com.backend.LibraryMangementSystem.DTO;

import com.backend.LibraryMangementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueBookResponseDTO {
    private String transanctionId;

    private String bookName;

    private TransactionStatus transactionStatus;
}
