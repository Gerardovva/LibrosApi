package org.gvasquez.desafio;

import org.gvasquez.desafio.principal.Principal;
import org.gvasquez.desafio.repository.AutorRepository;
import org.gvasquez.desafio.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private LibrosRepository repository;
    @Autowired
    private AutorRepository repositoryAutor;


    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    public void run(String... args) {
     Principal  principal=new Principal(repository,repositoryAutor);
       principal.muestraMenu();
    }
}
