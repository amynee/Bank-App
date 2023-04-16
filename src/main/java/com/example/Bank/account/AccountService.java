package com.example.Bank.account;

import com.example.Bank.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final ObjectsValidator<AccountRequest> validator;

    public Integer save(AccountRequest accountRequest) {
        validator.validate(accountRequest);
        return repository.save(
                mapper.toAccount(accountRequest)
        ).getId();
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
}
