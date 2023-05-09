package com.example.Bank.transaction;

import com.example.Bank.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TransactionMapper {
    public Transaction toTransaction(TransactionRequest request) {
        return Transaction.builder()
                .amount(request.getAmount())
                .destinationIban(request.getDestinationIban())
                .type(request.getType())
                .transactionDate(LocalDate.now())
                .user(
                        User.builder()
                            .id(request.getUserId())
                            .build()
                )
                .build();
    }

    public TransactionResponse toResponse(Transaction request) {
        return TransactionResponse.builder()
                .amount(request.getAmount())
                .destinationIban(request.getDestinationIban())
                .type(request.getType())
                .transactionDate(request.getTransactionDate())
                .build();
    }
}
