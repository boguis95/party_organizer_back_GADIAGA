package com.example.projet_party_gadiaga.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Data
@Entity
@Table(name = "parties", indexes = {
        @Index(name = "idx_party_name", columnList = "name"),
        @Index(name = "idx_party_location", columnList = "location")
})
@BatchSize(size = 10)
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;

    @Enumerated(EnumType.STRING)
    private PartyType type;

    private String date;
    private String time;
    private int remainingSlots;
    private int maxParticipants;
    private boolean isPaid;
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Set<Participant> participants;

    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private Set<Message> messages;

    // Getters and Setters
}