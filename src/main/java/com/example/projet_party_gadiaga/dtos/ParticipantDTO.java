package com.example.projet_party_gadiaga.dtos;

import lombok.Data;

@Data
public class ParticipantDTO {
    private Long id;
    private Long userId;
    private Long partyId;
    private String status;

    // Getters and Setters
}