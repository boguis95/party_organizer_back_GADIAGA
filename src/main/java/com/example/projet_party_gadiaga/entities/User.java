package com.example.projet_party_gadiaga.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Data
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email")
})
@BatchSize(size = 10)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;
    private int age;
    private String city;


    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Set<Party> parties;

    private float rating;


}