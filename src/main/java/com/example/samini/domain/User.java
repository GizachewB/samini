package com.example.samini.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SecondaryTable(name = "accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(table = "accounts", unique = true)
    private String email;

    @Column(table = "accounts")
    private String password;

    private String firstName;

    private String lastName;


    @ElementCollection
    private Collection<UserRole> roles;

//    public boolean isAdmin() {
//        return roles.stream().filter(r -> r.getRole().equals(UserRole.Role.ADMIN)).findAny().isPresent();
//    }

}
