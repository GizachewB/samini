package com.example.samini.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {
    public enum Role {
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    private Role role;
}
