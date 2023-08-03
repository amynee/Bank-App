package com.example.Bank.account;

import com.example.Bank.user.User;
import com.example.Bank.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
public class AccountServiceSpringTest {

    //@Autowired
    private AccountService service;

    //@Autowired
    private UserRepository userRepository;

   // @Test
    public void should_create_account() {
        var savedUser = userRepository.save(User.builder().firstName("Amine").build());
        var request = AccountRequest.builder()
                .userId(savedUser.getId())
                .build();

        var id = service.create(request);

        assertEquals(1, id);
    }
}
