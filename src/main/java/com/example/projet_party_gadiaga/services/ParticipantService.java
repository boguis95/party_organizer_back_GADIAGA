package com.example.projet_party_gadiaga.services;

import com.example.projet_party_gadiaga.dtos.ParticipantDTO;
import com.example.projet_party_gadiaga.entities.Participant;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.mappers.ParticipantMapper;
import com.example.projet_party_gadiaga.repositories.ParticipantRepository;
import com.example.projet_party_gadiaga.repositories.PartyRepository;
import com.example.projet_party_gadiaga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantMapper participantMapper;

    @Transactional
    public ParticipantDTO save(ParticipantDTO participantDTO) {
        Participant participant = participantMapper.toEntity(participantDTO);
        User user = userRepository.findById(participantDTO.getUserId()).orElse(null);
        Party party = partyRepository.findById(participantDTO.getPartyId()).orElse(null);
        participant.setUser(user);
        participant.setParty(party);
        return participantMapper.toDTO(participantRepository.save(participant));
    }


    @Transactional(readOnly = true)
    @Cacheable(value = "participants")
    public Page<ParticipantDTO> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable).map(participantMapper::toDTO);
    }


    @Transactional(readOnly = true)
    public ParticipantDTO findById(Long id) {
        return participantRepository.findById(id)
                .map(participantMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    @CacheEvict(value = "participants", key = "#id")
    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
}