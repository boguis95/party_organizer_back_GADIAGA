package com.example.projet_party_gadiaga.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String password;
    private int age;
    private String city;
    private float rating;

}