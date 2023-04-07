package com.example.Bank.account;

import com.example.Bank.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String iban;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
