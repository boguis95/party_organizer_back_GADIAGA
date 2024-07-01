package com.example.projet_party_gadiaga.services;


import com.example.projet_party_gadiaga.dtos.MessageDTO;
import com.example.projet_party_gadiaga.entities.Message;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.mappers.MessageMapper;
import com.example.projet_party_gadiaga.repositories.MessageRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private MessageMapper messageMapper;


    @Transactional
    public MessageDTO save(MessageDTO messageDTO) {
        Message message = messageMapper.toEntity(messageDTO);
        User sender = userRepository.findById(messageDTO.getSenderId()).orElse(null);
        User recipient = userRepository.findById(messageDTO.getRecipientId()).orElse(null);
        Party party = partyRepository.findById(messageDTO.getPartyId()).orElse(null);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setParty(party);
        return messageMapper.toDTO(messageRepository.save(message));
    }

    @CachePut(value = "messages", key = "#result.id")
    public MessageDTO sendMessage(Message message) {
        message.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return messageMapper.toDTO(messageRepository.save(message));
    }

    @Cacheable(value = "messages", key = "{#partyId, #recipientId}")
    public Page<MessageDTO> getMessages(Long partyId, Long recipientId, Pageable pageable) {
        return messageRepository.findByPartyIdAndRecipientId(partyId, recipientId, pageable)
                .map(messageMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        return messageRepository.findById(id)
                .map(messageMapper::toDTO)
                .orElse(null);
    }

    @CacheEvict(value = "messages", key = "#id")
    @Transactional
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}