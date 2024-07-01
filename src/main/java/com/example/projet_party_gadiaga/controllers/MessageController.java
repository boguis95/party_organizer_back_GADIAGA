package com.example.projet_party_gadiaga.controllers;

import com.example.projet_party_gadiaga.dtos.MessageDTO;
import com.example.projet_party_gadiaga.entities.Message;
import com.example.projet_party_gadiaga.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.findAll();
    }


    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        MessageDTO sentMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/{partyId}/{recipientId}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long partyId,
                                         @PathVariable Long recipientId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size
    ) {
        Page<MessageDTO> messages = messageService.getMessages(partyId, recipientId, PageRequest.of(page, size));
        return ResponseEntity.ok(messages.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        MessageDTO message = messageService.findById(id);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok(messageService.save(messageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}