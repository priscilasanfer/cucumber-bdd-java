package br.com.alura.leilao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeilaoApplication {

	
	//-Dspring.profiles.active=test na opção VM Option
	//-Dbrowser=firefox
	public static void main(String[] args) {
		SpringApplication.run(LeilaoApplication.class, args);
	}
	
}
