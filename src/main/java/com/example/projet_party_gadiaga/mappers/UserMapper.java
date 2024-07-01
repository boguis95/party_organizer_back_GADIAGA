package com.example.projet_party_gadiaga.mappers;

import com.example.projet_party_gadiaga.dtos.UserDTO;
import com.example.projet_party_gadiaga.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setCity(user.getCity());
        dto.setRating(user.getRating());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setCity(dto.getCity());
        user.setRating(dto.getRating());
        return user;
    }
}