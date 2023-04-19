package com.example.Bank.contact;

import com.example.Bank.account.AccountRequest;
import com.example.Bank.account.AccountResponse;
import com.example.Bank.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    public Integer save (
            @RequestBody ContactRequest contact
    ) {
        return service.create(contact);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<ContactResponse>> findAll(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(service.findAllByUser(id));
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactResponse> findById(
            @PathVariable("contact-id") Integer id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{contact-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable("contact-id") Integer id
    ) {
        service.delete(id);
    }
}
