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
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;
}
