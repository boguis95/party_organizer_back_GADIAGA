package com.example.projet_party_gadiaga.services;

import com.example.projet_party_gadiaga.dtos.RatingDTO;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.entities.Rating;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.mappers.RatingMapper;
import com.example.projet_party_gadiaga.repositories.PartyRepository;
import com.example.projet_party_gadiaga.repositories.RatingRepository;
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
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private RatingMapper ratingMapper;

    @Transactional
    @CachePut(value = "ratings", key = "#result.id")
    public RatingDTO save(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toEntity(ratingDTO);
        User user = userRepository.findById(ratingDTO.getUserId()).orElse(null);
        Party party = partyRepository.findById(ratingDTO.getPartyId()).orElse(null);
        rating.setUser(user);
        rating.setParty(party);
        return ratingMapper.toDTO(ratingRepository.save(rating));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "ratings")
    public Page<RatingDTO> findAll(Pageable pageable) {
        return   ratingRepository.findAll(pageable)
                .map(ratingMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public RatingDTO findById(Long id) {
        return ratingRepository.findById(id)
                .map(ratingMapper::toDTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    @CacheEvict(value = "ratings", key = "#id")
    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }
}