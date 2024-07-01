package com.example.projet_party_gadiaga.controllers;

import com.example.projet_party_gadiaga.dtos.PartyDTO;
import com.example.projet_party_gadiaga.entities.Party;
import com.example.projet_party_gadiaga.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public ResponseEntity<List<PartyDTO>> getParties(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Page<PartyDTO> parties = partyService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(parties.getContent());
    }

    @GetMapping("/location")
    public ResponseEntity<Page<PartyDTO>> getPartiesByLocation(@RequestParam String location,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        Page<PartyDTO> parties = partyService.getPartiesByLocation(location, PageRequest.of(page, size));
        return ResponseEntity.ok(parties);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PartyDTO> getPartyById(@PathVariable Long id) {
        PartyDTO party = partyService.findById(id);
        return party != null ? ResponseEntity.ok(party) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) {
        return ResponseEntity.ok(partyService.save(partyDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateParty(@PathVariable Long id, @RequestBody Party party) {
        Party updatedParty = partyService.updateParty(id, party);
        return ResponseEntity.ok(updatedParty);
    }

    @GetMapping("/search")
    public List<Party> searchParties(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String partyType,
            @RequestParam(required = false) Integer maxPeople,
            @RequestParam(required = false) Boolean isPaid,
            @RequestParam(required = false) String time) {
        return partyService.searchParties(city, partyType, maxPeople, isPaid, time);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable Long id, @RequestBody PartyDTO partyDTO) {
        PartyDTO existingParty = partyService.findById(id);
        if (existingParty != null) {
            partyDTO.setId(id);
            return ResponseEntity.ok(partyService.save(partyDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{partyId}/participate")
    public ResponseEntity<?> participate(@PathVariable Long partyId, @RequestBody Long userId) {
        try {
            partyService.addParticipant(partyId, userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        partyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}