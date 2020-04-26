package com.proxym.pfe.gestionAssociationBack;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant.Etudiant;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant.EtudiantRepository;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionAssociationBackApplication {

  public enum Couleur {
        MARRON("marron"),
        BLEU("bleu"),
        VERT("vert"),
        VERRON("verron"),
        INCONNU("non déterminé"),
        ROUGE("rouge mais j'avais piscine...");

        private String name = "";

        Couleur(String n) {
            name = n;
        }

        public String toString() {
            return name;
        }
    }

    public static class Personne {

        public Double taille = 0.0d, poids = 0.0d;
        public String nom = "", prenom = "";
        public Couleur yeux = Couleur.INCONNU;

        public Personne() {
        }

        public Personne(double taille, double poids, String nom, String prenom, Couleur yeux) {
            super();
            this.taille = taille;
            this.poids = poids;
            this.nom = nom;
            this.prenom = prenom;
            this.yeux = yeux;
        }

        public String toString() {
            String s = "Je m'appelle " + nom + " " + prenom;
            s += ", je pèse " + poids + " Kg";
            s += ", et je mesure " + taille + " cm.";
            return s;
        }

        public Double getTaille() {
            return taille;
        }

        public void setTaille(Double taille) {
            this.taille = taille;
        }

        public Double getPoids() {
            return poids;
        }

        public void setPoids(Double poids) {
            this.poids = poids;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public Couleur getYeux() {
            return yeux;
        }

        public void setYeux(Couleur yeux) {
            this.yeux = yeux;
        }
    }

    public static void main(String[] args) throws ParseException {



        ApplicationContext ctx = SpringApplication.run(GestionAssociationBackApplication.class, args);
        List<Personne> listP = Arrays.asList(
                new Personne(1.80, 70, "A", "Nicolas", Couleur.BLEU),
                new Personne(1.56, 50, "B", "Nicole", Couleur.VERRON),
                new Personne(1.75, 65, "C", "Germain", Couleur.VERT),
                new Personne(1.68, 50, "D", "Michel", Couleur.ROUGE),
                new Personne(1.96, 65, "E", "Cyrille", Couleur.BLEU),
                new Personne(2.10, 120, "F", "Denis", Couleur.ROUGE),
                new Personne(1.90, 90, "G", "Olivier", Couleur.VERRON)
        );

        Stream<Personne> sp = listP.stream();
        System.out.println("stream peronne" + sp);

        System.out.println("\nAprès le filtre et le map et reduce");
        // sp = listP.stream();

        //sp = listP.stream();

        List<Double> ld = sp.map(x -> x.getPoids())
                .collect(Collectors.toList());

        System.out.println("ld.iterator();" + ld);


       /* EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       /* etudiantRepository.save(new Etudiant("ikrlll", "ik@gmail.com", df.parse("2020-10-10"), "ahmed.png"));
        etudiantRepository.save(new Etudiant("llllrl", "ll@gmail.com", df.parse("2020-10-10"), "lie.png"));
        etudiantRepository.save(new Etudiant("nlllllrn", "nn@gmail.com", df.parse("2020-10-10"), "amin.png"));
        etudiantRepository.save(new Etudiant("Ahrrmed", "ahmed@gmail.com", df.parse("2020-10-10"), "ahmed.png"));
        etudiantRepository.save(new Etudiant("lhhrrie", "lie@gmail.com", df.parse("2020-10-10"), "lie.png"));
        etudiantRepository.save(new Etudiant("Amirhhn", "amin@gmail.com", df.parse("2020-10-10"), "amin.png"));
       //je veux 5 etudiant de la page 5
        */
       /* Page<Etudiant> etudiants = etudiantRepository.chercherEtudiant("%A%", new PageRequest(0, 5));
        etudiants.forEach(etudiant -> System.out.println(etudiant.getNom()
        ));*/
    }

}
