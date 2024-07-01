package com.example.projet_party_gadiaga.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "ratings", indexes = {
        @Index(name = "idx_rating_user", columnList = "user_id"),
        @Index(name = "idx_rating_party", columnList = "party_id")
})
@BatchSize(size = 10)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    private int rating;
    private String comment;
    private String createdAt;

}
