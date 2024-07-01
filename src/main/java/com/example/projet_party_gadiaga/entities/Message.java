package com.example.projet_party_gadiaga.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;


@Data
@Entity
@Table(name = "messages", indexes = {
        @Index(name = "idx_message_sender", columnList = "sender_id"),
        @Index(name = "idx_message_recipient", columnList = "recipient_id"),
        @Index(name = "idx_message_party", columnList = "party_id")
})
@BatchSize(size = 10)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(nullable = false)
    private String content;

    private String createdAt;

}