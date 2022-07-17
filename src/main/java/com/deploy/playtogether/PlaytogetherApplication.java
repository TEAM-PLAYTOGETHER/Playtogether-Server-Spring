package com.deploy.playtogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlaytogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaytogetherApplication.class, args);
	}

}
