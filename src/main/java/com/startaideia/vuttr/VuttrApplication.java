package com.startaideia.vuttr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação
 * @author robso
 *
 */
@SpringBootApplication
public class VuttrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuttrApplication.class, args);
		System.out.println("Project Started Successfully!");
	}

}
