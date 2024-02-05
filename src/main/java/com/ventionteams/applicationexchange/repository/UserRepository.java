package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
