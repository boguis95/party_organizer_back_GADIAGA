package com.example.projet_party_gadiaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjetPartyGadiagaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetPartyGadiagaApplication.class, args);
	}

}
