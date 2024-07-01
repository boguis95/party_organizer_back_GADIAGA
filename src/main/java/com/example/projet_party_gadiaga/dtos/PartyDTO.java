package com.example.projet_party_gadiaga.dtos;


import lombok.Data;

@Data
public class PartyDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String type;
    private String date;
    private String time;
    private int remainingSlots;
    private int maxParticipants;
    private boolean isPaid;
    private float price;
    private Long organizerId;

    // Getters and Setters
}