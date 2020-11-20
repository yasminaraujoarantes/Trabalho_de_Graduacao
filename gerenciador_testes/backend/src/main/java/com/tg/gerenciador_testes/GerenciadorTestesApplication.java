package com.tg.gerenciador_testes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({ "com.tg.gerenciador_testes.repositories" })
public class GerenciadorTestesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTestesApplication.class, args);
	}

}
