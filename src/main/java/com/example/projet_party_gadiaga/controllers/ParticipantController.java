package com.example.projet_party_gadiaga.controllers;

import com.example.projet_party_gadiaga.dtos.ParticipantDTO;
import com.example.projet_party_gadiaga.services.ParticipantService;
import com.example.projet_party_gadiaga.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private PartyService partyService;



    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getParticipants(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ParticipantDTO> participants = participantService.findAll(pageable);
        return ResponseEntity.ok(participants.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long id) {
        ParticipantDTO participant = participantService.findById(id);
        return participant != null ? ResponseEntity.ok(participant) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant(@RequestBody ParticipantDTO participantDTO) {
        return ResponseEntity.ok(participantService.save(participantDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}