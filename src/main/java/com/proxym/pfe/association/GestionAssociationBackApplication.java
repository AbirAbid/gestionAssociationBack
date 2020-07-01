package com.proxym.pfe.association;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class GestionAssociationBackApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GestionAssociationBackApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

    }
}
