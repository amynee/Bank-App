package com.example.Bank.account;

import com.example.Bank.exception.OperationNonPermittedException;
import com.example.Bank.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final ObjectsValidator<AccountRequest> validator;

    public Integer create(AccountRequest accountRequest) {
        validator.validate(accountRequest);
        var account = mapper.toAccount(accountRequest);
        var userHasAlreadyAnAccount = repository.existsByUserId(accountRequest.getUserId());
        if (userHasAlreadyAnAccount) {
            throw new OperationNonPermittedException("The selected user has an account");
        }
        account.setIban(generateRandomIban());
        return repository.save(account).getId();
    }

    @Transactional
    public List<AccountResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Account found with the ID: " + id));
    }

    public void delete (Integer id) {
        // check before delete
        repository.deleteById(id);
    }

    private String generateRandomIban() {
        // generate a random IBAN
        var iban = Iban.random(CountryCode.TN).toFormattedString();
        // check if the iban exists
        if (repository.existsByIban(iban)) {
            // if true --> generate new iban
            generateRandomIban();
        }
        // if false (not exist) --> return IBAN
        return iban;
    }
}
