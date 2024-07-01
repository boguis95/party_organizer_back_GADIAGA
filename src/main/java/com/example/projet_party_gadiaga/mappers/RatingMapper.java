package com.example.projet_party_gadiaga.mappers;

import com.example.projet_party_gadiaga.dtos.RatingDTO;
import com.example.projet_party_gadiaga.entities.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    public RatingDTO toDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setUserId(rating.getUser().getId());
        dto.setPartyId(rating.getParty().getId());
        dto.setRating(rating.getRating());
        dto.setComment(rating.getComment());
        dto.setCreatedAt(rating.getCreatedAt());
        return dto;
    }

    public Rating toEntity(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setRating(dto.getRating());
        rating.setComment(dto.getComment());
        rating.setCreatedAt(dto.getCreatedAt());
        return rating;
    }
}