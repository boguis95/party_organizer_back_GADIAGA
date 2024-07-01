package com.example.projet_party_gadiaga.mappers;


import com.example.projet_party_gadiaga.dtos.PartyDTO;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.entities.PartyType;
import org.springframework.stereotype.Component;

@Component
public class PartyMapper {
    public PartyDTO toDTO(Party party) {
        PartyDTO dto = new PartyDTO();
        dto.setId(party.getId());
        dto.setName(party.getName());
        dto.setLocation(party.getLocation());
        dto.setDescription(party.getDescription());
        dto.setType(party.getType().name());
        dto.setDate(party.getDate());
        dto.setTime(party.getTime());
        dto.setRemainingSlots(party.getRemainingSlots());
        dto.setMaxParticipants(party.getMaxParticipants());
        dto.setPaid(party.isPaid());
        dto.setPrice(party.getPrice());
        dto.setOrganizerId(party.getOrganizer().getId());
        return dto;
    }

    public Party toEntity(PartyDTO dto) {
        Party party = new Party();
        party.setId(dto.getId());
        party.setName(dto.getName());
        party.setLocation(dto.getLocation());
        party.setDescription(dto.getDescription());
        party.setType(PartyType.valueOf(dto.getType()));
        party.setDate(dto.getDate());
        party.setTime(dto.getTime());
        party.setRemainingSlots(dto.getRemainingSlots());
        party.setMaxParticipants(dto.getMaxParticipants());
        party.setPaid(dto.isPaid());
        party.setPrice(dto.getPrice());
        return party;
    }
}