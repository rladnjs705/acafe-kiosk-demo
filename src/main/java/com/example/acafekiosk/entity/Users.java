package com.example.acafekiosk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) // Null이 되면 안됨.
    private String password;

    @Column(nullable=false, unique=true) //unique=true 해야함.
    private String email;

    @OneToMany(mappedBy = "users")
    private List<UserRole> userRoles = new ArrayList<>();
}
