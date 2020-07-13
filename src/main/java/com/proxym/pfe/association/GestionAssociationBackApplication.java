package com.proxym.pfe.association;

import com.proxym.pfe.association.test.Film;
import com.proxym.pfe.association.test.FilmRepo;
import com.proxym.pfe.association.test.RealisRep;
import com.proxym.pfe.association.test.Realisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GestionAssociationBackApplication implements CommandLineRunner {
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    RealisRep realisRep;

    public static void main(String[] args) {


        SpringApplication.run(GestionAssociationBackApplication.class, args);


    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        Film f = new Film("titre");
        filmRepo.save(f);

        Realisateur r = new Realisateur("nom");

        Set<Film> films = new HashSet<>();
        films.add(f);
        r.setFilms(films);
        f.setRealisateur(r);
        realisRep.save(r);


    }
}
