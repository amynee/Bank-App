package com.example.Bank.contact;

import com.example.Bank.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;

    private final ObjectsValidator<ContactRequest> validator;

    private final ContactMapper mapper;

    public Integer create(ContactRequest request) {
        validator.validate(request);
        var contact = mapper.toContact(request);
        return  repository.save(contact).getId();
    }

    public List<ContactResponse> findAllByUser(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ContactResponse findById(Integer contactId) {
        return repository.findById(contactId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("No contact found with the ID: " + contactId));
    }

    public void delete(Integer contactId) {
        repository.deleteById(contactId);
    }
}
