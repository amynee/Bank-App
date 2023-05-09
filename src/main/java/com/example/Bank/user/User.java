package com.example.Bank.user;

import com.example.Bank.account.Account;
import com.example.Bank.contact.Contact;
import com.example.Bank.role.Role;
import com.example.Bank.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean active;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @ManyToMany
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
