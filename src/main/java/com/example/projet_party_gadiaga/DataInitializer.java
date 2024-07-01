package com.example.projet_party_gadiaga;

import com.example.projet_party_gadiaga.entities.*;
import com.example.projet_party_gadiaga.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            // Create users
            User user1 = new User();
            user1.setUsername("john_doe");
            user1.setEmail("john@example.com");
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setName("John Doe");
            user1.setAge(30);
            user1.setCity("Paris");
            userRepository.save(user1);

            User user2 = new User();
            user2.setUsername("jane_smith");
            user2.setEmail("jane@example.com");
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setName("Jane Smith");
            user2.setAge(25);
            user2.setCity("Lyon");
            userRepository.save(user2);

            User user3 = new User();
            user3.setUsername("paul_Lingard");
            user3.setEmail("paul@example.com");
            user3.setPassword(passwordEncoder.encode("password"));
            user3.setName("paul Lingard");
            user3.setAge(25);
            user3.setCity("Paris");
            userRepository.save(user3);

            // Create party
            Party party1 = new Party();
            party1.setName("John's Birthday Party");
            party1.setLocation("John's House");
            party1.setDescription("A fun birthday party with games and music.");
            party1.setType(PartyType.CLASSIC);
            party1.setDate("2023-07-01");
            party1.setTime("18:00");
            party1.setRemainingSlots(10);
            party1.setMaxParticipants(20);
            party1.setPaid(false);
            party1.setPrice(0);
            party1.setOrganizer(user1);
            partyRepository.save(party1);

            Party party2 = new Party();
            party2.setName("Do's Birthday Party");
            party2.setLocation("Do's House");
            party2.setDescription("A fun birthday party with games and music.");
            party2.setType(PartyType.CLASSIC);
            party2.setDate("2023-09-01");
            party2.setTime("19:00");
            party2.setRemainingSlots(10);
            party2.setMaxParticipants(20);
            party2.setPaid(false);
            party2.setPrice(0);
            party2.setOrganizer(user2);
            partyRepository.save(party2);

            Party party3 = new Party();
            party3.setName("Paul's Birthday Party");
            party3.setLocation("Paul's House");
            party3.setDescription("A fun birthday party with games and music.");
            party3.setType(PartyType.CLASSIC);
            party3.setDate("2024-06-01");
            party3.setTime("19:00");
            party3.setRemainingSlots(10);
            party3.setMaxParticipants(20);
            party3.setPaid(true);
            party3.setPrice(0);
            party3.setOrganizer(user3);
            partyRepository.save(party3);

            // Create participant
            Participant participant1 = new Participant();
            participant1.setUser(user2);
            participant1.setParty(party1);
            participant1.setStatus(Status.CONFIRMED);
            participantRepository.save(participant1);

            Participant participant2 = new Participant();
            participant2.setUser(user1);
            participant2.setParty(party2);
            participant2.setStatus(Status.CONFIRMED);
            participantRepository.save(participant2);

            // Create message
            Message message1 = new Message();
            message1.setSender(user1);
            message1.setRecipient(user2);
            message1.setParty(party1);
            message1.setContent("Looking forward to seeing you at the party!");
            message1.setCreatedAt("2023-06-20T10:00:00");
            messageRepository.save(message1);

            // Create rating
            Rating rating1 = new Rating();
            rating1.setUser(user2);
            rating1.setParty(party1);
            rating1.setRating(5);
            rating1.setComment("Great party!");
            rating1.setCreatedAt("2023-07-02T12:00:00");
            ratingRepository.save(rating1);
        };
    }
}