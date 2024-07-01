package com.example.projet_party_gadiaga.repositories;


import com.example.projet_party_gadiaga.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findByPartyIdAndRecipientId(Long partyId, Long recipientId, Pageable pageable);}