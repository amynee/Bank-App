package com.example.Bank.transaction;

import com.example.Bank.contact.ContactRequest;
import com.example.Bank.contact.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public Integer save (
            @RequestBody TransactionRequest transaction
    ) {
        return service.create(transaction);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<TransactionResponse>> findAll(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(service.findAllByUser(id));
    }
}
