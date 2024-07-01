package com.example.projet_party_gadiaga.repositories;

import com.example.projet_party_gadiaga.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}