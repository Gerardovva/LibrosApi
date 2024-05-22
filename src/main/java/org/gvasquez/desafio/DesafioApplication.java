package org.gvasquez.desafio;

import org.gvasquez.desafio.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    public void run(String... args) {
     Principal  principal=new Principal();
       principal.muestraMenu();
    }
}
