package com.example.Bank.account;

import com.example.Bank.user.User;
import com.example.Bank.validator.ObjectsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapper mapper;

    @Mock
    private ObjectsValidator<AccountRequest> validator;

    @InjectMocks
    private AccountService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_account() {
        var request = AccountRequest.builder()
                .userId(1)
                .build();

        var account = Account.builder()
                .id(1)
                .user(User.builder().id(1).build())
                .build();

        when(repository.existsByUserId(request.getUserId()))
                .thenReturn(false);
        when(mapper.toAccount(request)).thenReturn(account);

    }
}