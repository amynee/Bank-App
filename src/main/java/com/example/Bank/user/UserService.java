package com.example.Bank.user;

import com.example.Bank.account.Account;
import com.example.Bank.account.AccountRequest;
import com.example.Bank.account.AccountService;
import com.example.Bank.transaction.TransactionRepository;
import com.example.Bank.transaction.TransactionType;
import com.example.Bank.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectsValidator<UserRequest> validator;
    private final UserMapper mapper;
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder encoder;

    public Integer create(UserRequest request) {
        validator.validate(request);
        var user = mapper.toUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setActive(false);
        return repository.save(user).getId();
    }

    @Transactional
    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID: " + id));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Integer validateAccount(Integer id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID for account validation: " + id));

        if (user.getAccount() == null) {
            //create a bank account
            var account = AccountRequest.builder()
                    .userId(id)
                    .build();
            var savedAccountId = accountService.create(account);
            user.setAccount(
                    Account.builder()
                            .id(savedAccountId)
                            .build()
            );
        }

        user.setActive(true);
        return repository.save(user).getId();
    }

    public Integer invaildateAccount(Integer id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID for account invalidation: " + id));

        user.setActive(false);
        return repository.save(user).getId();
    }

    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    public BigDecimal highestTransfer(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
