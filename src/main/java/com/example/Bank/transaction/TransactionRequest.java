package com.example.Bank.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount should be positive")
    @Min(1)
    private BigDecimal amount;

    @NotNull(message = "IBAN is mandatory")
    private String destinationIban;

    @NotNull(message = "Type is mondatory")
    private TransactionType type;

    @NotNull(message = "User is mondatory")
    private Integer userId;
}
