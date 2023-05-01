package com.example.Bank.account;

import com.example.Bank.exception.ObjectValidationException;
import com.example.Bank.exception.OperationNonPermittedException;
import com.example.Bank.user.User;
import com.example.Bank.validator.ObjectsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
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
                .id(10)
                .user(User.builder().id(1).build())
                .build();

        when(repository.existsByUserId(request.getUserId())).thenReturn(false);
        when(mapper.toAccount(request)).thenReturn(account);
        when(repository.existsByIban(anyString())).thenReturn(false);
        when(repository.save(account)).thenReturn(account);

        var id = service.create(request);
        verify(validator).validate(request);
        verify(mapper).toAccount(request);
        verify(repository).existsByUserId(request.getUserId());
        verify(repository).existsByIban(notNull());
        verify(repository).save(account);

        assertEquals(10, id);

    }

    @Test
    public void should_throw_operation_non_permitted_exception_if_user_has_account_1() {
        var request = AccountRequest.builder().userId(1).build();
        when(repository.existsByUserId(request.getUserId())).thenReturn(true);
        var exp = assertThrows(OperationNonPermittedException.class, () -> service.create(request));
        assertEquals("The selected user has an account", exp.getMessage());
    }

    //@Test
    //public void should_throw_object_validation_exception() {
    //    var request = AccountRequest.builder().build();
        // can not mock a void
    //    when(validator.validate(request))
    //    var exp = assertThrows(ObjectValidationException.class, () -> service.create(request));
    //    assertEquals("The selected user has an account", exp.getMessage());
    //}
}