package com.spring.apivendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.spring.apivendas.entity.Cliente;
import com.spring.apivendas.repository.ClientesRepository;

@SpringBootApplication
public class ApiVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository) {
		return args -> {
			Cliente c = new Cliente(null,"Otávio");
			clientesRepository.save(c);
		};
		
	}

}
