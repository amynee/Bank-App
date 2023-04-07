package com.example.Bank.contact;

import com.example.Bank.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
