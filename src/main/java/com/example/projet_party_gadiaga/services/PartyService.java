package com.example.projet_party_gadiaga.services;

import com.example.projet_party_gadiaga.dtos.PartyDTO;
import com.example.projet_party_gadiaga.entities.Participant;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.exceptions.ResourceNotFoundException;
import com.example.projet_party_gadiaga.mappers.PartyMapper;
import com.example.projet_party_gadiaga.repositories.ParticipantRepository;
import com.example.projet_party_gadiaga.repositories.PartyRepository;
import com.example.projet_party_gadiaga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyMapper partyMapper;

    @Transactional
    @CachePut(value = "parties", key = "#result.id")
    public PartyDTO save(PartyDTO partyDTO) {
        Party party = partyMapper.toEntity(partyDTO);
        User organizer = userRepository.findById(partyDTO.getOrganizerId()).orElse(null);
        party.setOrganizer(organizer);
        return partyMapper.toDTO(partyRepository.save(party));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "parties")
    public Page<PartyDTO> findAll(Pageable pageable) {
        return partyRepository.findAll(pageable).map(partyMapper::toDTO);
    }

    @Cacheable(value = "parties", key = "#location")
    public Page<PartyDTO> getPartiesByLocation(String location, Pageable pageable) {
        return partyRepository.findByLocation(location, pageable).map(partyMapper::toDTO);

    }


    @CachePut(value = "parties", key = "#id")
    public Party updateParty(Long id, Party partyDetails) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found"));
        party.setName(partyDetails.getName());
        party.setLocation(partyDetails.getLocation());
        party.setType(partyDetails.getType());
        party.setDate(partyDetails.getDate());
        party.setTime(partyDetails.getTime());
        party.setRemainingSlots(partyDetails.getRemainingSlots());
        party.setMaxParticipants(partyDetails.getMaxParticipants());
        party.setPaid(partyDetails.isPaid());
        party.setPrice(partyDetails.getPrice());
        return partyRepository.save(party);
    }

    @CacheEvict(value = "parties", key = "#id")
    public void deleteParty(Long id) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found"));
        partyRepository.delete(party);
    }

    public List<Party> searchParties(String city, String partyType, Integer maxPeople, Boolean isPaid, String time) {
        return partyRepository.findAll().stream()
                .filter(p -> (city == null || p.getLocation().equalsIgnoreCase(city)))
                .filter(p -> (partyType == null || p.getType().toString().equalsIgnoreCase(partyType)))
                .filter(p -> (maxPeople == null || p.getMaxParticipants() >= maxPeople))
                .filter(p -> (isPaid == null || p.isPaid() == isPaid))
                .filter(p -> (time == null || p.getTime().equalsIgnoreCase(time)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PartyDTO findById(Long id) {
        return partyRepository.findById(id)
                .map(partyMapper::toDTO)
                .orElse(null);
    }

    public void addParticipant(Long partyId, Long userId) {
        Optional<Party> partyOpt = partyRepository.findById(partyId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (partyOpt.isPresent() && userOpt.isPresent()) {
            Participant participant = new Participant();
            participant.setParty(partyOpt.get());
            participant.setUser(userOpt.get());
            participant.setApproved(false);  // Initially not approved
            participantRepository.save(participant);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        partyRepository.deleteById(id);
    }
}