package com.example.projet_party_gadiaga.services;

import com.example.projet_party_gadiaga.dtos.UserDTO;
import com.example.projet_party_gadiaga.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.repositories.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO registerUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user bien enregistré");
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }
}