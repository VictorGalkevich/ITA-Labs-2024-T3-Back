package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;


@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User extends AuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;

    @Column(nullable = false, name = "password")
    private String password;
}
