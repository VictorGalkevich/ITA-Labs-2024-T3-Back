package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
