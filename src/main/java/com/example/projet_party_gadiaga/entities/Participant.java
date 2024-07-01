package com.example.projet_party_gadiaga.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "participants", indexes = {
        @Index(name = "idx_participant_user", columnList = "user_id"),
        @Index(name = "idx_participant_party", columnList = "party_id")
})
@BatchSize(size = 10)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Enumerated(EnumType.STRING)
    private Status status;


}