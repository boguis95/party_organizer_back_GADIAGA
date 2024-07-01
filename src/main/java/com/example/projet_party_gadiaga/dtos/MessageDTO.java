package com.example.projet_party_gadiaga.dtos;


import lombok.Data;

@Data
public class MessageDTO {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private Long partyId;
    private String content;
    private String createdAt;
}