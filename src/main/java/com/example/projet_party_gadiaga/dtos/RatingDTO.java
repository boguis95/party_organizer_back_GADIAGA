package com.example.projet_party_gadiaga.dtos;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private Long userId;
    private Long partyId;
    private int rating;
    private String comment;
    private String createdAt;

}