package com.example.Bank.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String iban;

    private boolean active;
}
