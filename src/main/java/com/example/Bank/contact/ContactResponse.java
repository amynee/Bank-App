package com.example.Bank.contact;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String iban;
}
