package com.example.Bank.user;

import com.example.Bank.contact.ContactRequest;
import com.example.Bank.contact.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public Integer save (
            @RequestBody UserRequest user
    ) {
        return service.create(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/validate/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer validate (
            @PathVariable("user-id") Integer id
    ) {
        return service.validateAccount(id);
    }

    @PatchMapping("/invalidate/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer invalidate (
            @PathVariable("user-id") Integer id
    ) {
        return service.invaildateAccount(id);
    }

    @GetMapping("/transactions/highest/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getHighestTransfert(
            @PathVariable("user-id") Integer userId
    ) {
        return  service.highestTransfer(userId);
    }

    @GetMapping("/transactions/lowest/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getHighestDeposit(
            @PathVariable("user-id") Integer userId
    ) {
        return  service.highestDeposit(userId);
    }
}
