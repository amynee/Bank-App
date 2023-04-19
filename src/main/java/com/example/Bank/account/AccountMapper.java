package com.example.Bank.account;

import com.example.Bank.user.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toAccount(AccountRequest request) {
       return Account.builder()
                .user(
                        User.builder()
                                .id(request.getUserId())
                                .build()
                )
                .build();
    }

    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .iban(account.getIban())
                .userFirstName(account.getUser().getFirstName())
                .userFirstName(account.getUser().getLastName())
                .build();
    }
}
