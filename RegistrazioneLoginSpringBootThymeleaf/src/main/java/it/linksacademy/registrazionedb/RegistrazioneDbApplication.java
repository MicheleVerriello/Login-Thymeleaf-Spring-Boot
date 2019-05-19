package it.linksacademy.registrazionedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories //Abilita la JpaRepositories in modo da permettere le operazioni CRUD
public class RegistrazioneDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrazioneDbApplication.class, args);
	}

}
