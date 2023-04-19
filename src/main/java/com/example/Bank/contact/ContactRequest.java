package com.example.Bank.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {

    private Integer id;

    @NotNull(message = "First name is mandatory")
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Email name is mandatory")
    @Email(message = "Email should be a valid address")
    private String email;

    @NotNull(message = "IBAN is mandatory")
    private String iban;

    @NotNull(message = "User is mandatory")
    private Integer userId;
}
