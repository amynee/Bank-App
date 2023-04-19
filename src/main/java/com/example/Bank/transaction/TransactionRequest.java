package com.example.Bank.transaction;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    private BigDecimal amount;

    private String destinationIban;

    private TransactionType type;

    private Integer userId;
}
