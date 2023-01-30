package com.example.acafekiosk.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles roles;
}
