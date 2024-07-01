package com.example.projet_party_gadiaga.mappers;

import com.example.projet_party_gadiaga.dtos.MessageDTO;
import com.example.projet_party_gadiaga.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageDTO toDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setRecipientId(message.getRecipient().getId());
        dto.setPartyId(message.getParty().getId());
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        return dto;
    }

    public Message toEntity(MessageDTO dto) {
        Message message = new Message();
        message.setId(dto.getId());
        message.setContent(dto.getContent());
        message.setCreatedAt(dto.getCreatedAt());
        return message;
    }
}