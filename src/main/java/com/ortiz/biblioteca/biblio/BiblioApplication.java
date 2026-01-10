package com.ortiz.biblioteca.biblio;

import com.ortiz.biblioteca.biblio.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiblioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BiblioApplication.class, args);
	}
    private final Principal principal;

    @Autowired
    public BiblioApplication(Principal principal){
        this.principal = principal;
    }


    @Override
    public void run(String... args) throws Exception {
  //Principal principal = new Principal();
        principal.muestraMenu();


    }
}
