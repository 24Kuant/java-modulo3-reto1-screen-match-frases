package com.aluracursos.screematch.administrafrasescelebres;

import com.aluracursos.screematch.administrafrasescelebres.principal.Principal;
import com.aluracursos.screematch.administrafrasescelebres.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdministraFrasesCelebresApplication implements CommandLineRunner {
	@Autowired  //Inyecta la dependencia de SerieRepository, es una clase gestionada por Spring JPA, no por el usuario.
	private FraseRepository repository;

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(AdministraFrasesCelebresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Principal principal = new Principal(repository);  //no e puede crear con new si la gestiona Spring
		principal.muestraElMenu();
	}

}
