package it.prova.gestionetratte;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.AirbusService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner  {

	@Autowired
	private AirbusService airbusService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Mi carico dal service un airbus
		Airbus trova = airbusService.trovaPerCodice("Ababa");

		if (trova == null) {
			Airbus airbus = new Airbus("Ababa", "Roma é Bella", new Date(), 300);
			airbusService.inserisciNuovo(airbus);
		}
	}
	
	
	
	

}
