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
public class Roles
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String rolename;

    @OneToMany(mappedBy="roles")
    private List<UserRole> users = new ArrayList<>();

    public Roles(String rolename) {
        this.rolename = rolename;
    }
}
