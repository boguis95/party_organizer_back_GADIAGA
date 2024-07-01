package com.example.projet_party_gadiaga.mappers;

import com.example.projet_party_gadiaga.dtos.ParticipantDTO;
import com.example.projet_party_gadiaga.entities.Participant;
import com.example.projet_party_gadiaga.entities.Status;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {
    public ParticipantDTO toDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserId(participant.getUser().getId());
        dto.setPartyId(participant.getParty().getId());
        dto.setStatus(participant.getStatus().name());
        return dto;
    }

    public Participant toEntity(ParticipantDTO dto) {
        Participant participant = new Participant();
        participant.setId(dto.getId());
        participant.setStatus(Status.valueOf(dto.getStatus()));
        return participant;
    }
}