package com.example.Bank.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotNull(message = "First name is mondatory")
    private String firstName;

    @NotNull(message = "Last name is mondatory")
    private String lastName;

    @NotNull(message = "Email is mondatory")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is mondatory")
    @Size(min = 4, max = 16, message = "Password should be between 4 and 8 chars")
    private String password;
}
