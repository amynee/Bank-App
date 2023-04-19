package com.example.Bank.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRequest request) {
        return User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .active(user.isActive())
                .iban(user.getAccount().getIban())
                .build();
    }
}
