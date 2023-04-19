package com.example.Bank.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private BigDecimal amount;

    private String destinationIban;

    private TransactionType type;

    private LocalDate transactionDate;
}
