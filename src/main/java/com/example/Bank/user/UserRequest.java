package com.example.Bank.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
