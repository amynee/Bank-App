package com.example.Bank.contact;

import com.example.Bank.user.User;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public Contact toContact(ContactRequest request) {
        return Contact.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .iban(request.getIban())
                .user(
                        User.builder()
                            .id(request.getUserId())
                            .build()
                )
                .build();
    }

    public ContactResponse toResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .build();
    }
}
