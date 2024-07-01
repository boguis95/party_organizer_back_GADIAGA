package com.example.projet_party_gadiaga.repositories;

import com.example.projet_party_gadiaga.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}