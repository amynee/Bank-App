package com.example.Bank.contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String iban;

    private Integer userId;
}
