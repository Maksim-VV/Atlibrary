package com.vasiliska.Atlibrary.repository;

import com.vasiliska.Atlibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
