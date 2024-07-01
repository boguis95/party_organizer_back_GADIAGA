package com.example.projet_party_gadiaga.repositories;

import com.example.projet_party_gadiaga.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}