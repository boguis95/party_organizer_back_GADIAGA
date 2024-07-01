package com.example.projet_party_gadiaga.repositories;

import com.example.projet_party_gadiaga.entities.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
    Page<Party> findByLocation(String location, Pageable pageable);

}
