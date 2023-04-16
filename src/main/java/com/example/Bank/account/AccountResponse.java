package com.example.Bank.account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    private Integer id;

    private String iban;

    private String userFirstName;

    private String userLastName;
}
