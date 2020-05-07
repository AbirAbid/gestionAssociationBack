package com.proxym.pfe.gestionAssociationBack;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;
import com.proxym.pfe.gestionAssociationBack.bookPackage.BookRepositories;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Publisher;
import com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories.BookPublisher;
import com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories.PublisherRepository;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.MissionRepository;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*

@SpringBootApplication
public class GestionAssociationBackApplication {

    @Autowired
    private BookRepositories bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;
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

     */
/*   List<Personne> listP = Arrays.asList(
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


       *//*
 */
/* EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       /* etudiantRepository.save(new Etudiant("ikrlll", "ik@gmail.com", df.parse("2020-10-10"), "ahmed.png"));
        etudiantRepository.save(new Etudiant("llllrl", "ll@gmail.com", df.parse("2020-10-10"), "lie.png"));
        etudiantRepository.save(new Etudiant("nlllllrn", "nn@gmail.com", df.parse("2020-10-10"), "amin.png"));
        etudiantRepository.save(new Etudiant("Ahrrmed", "ahmed@gmail.com", df.parse("2020-10-10"), "ahmed.png"));
        etudiantRepository.save(new Etudiant("lhhrrie", "lie@gmail.com", df.parse("2020-10-10"), "lie.png"));
        etudiantRepository.save(new Etudiant("Amirhhn", "amin@gmail.com", df.parse("2020-10-10"), "amin.png"));
       //je veux 5 etudiant de la page 5
        *//*

 */
/* Page<Etudiant> etudiants = etudiantRepository.chercherEtudiant("%A%", new PageRequest(0, 5));
        etudiants.forEach(etudiant -> System.out.println(etudiant.getNom()
        ));*//*

    }

}
*/
@SpringBootApplication
public class GestionAssociationBackApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(GestionAssociationBackApplication.class);

    @Autowired
    private BookRepositories bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MissionRepository missionRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionAssociationBackApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        // create new
        Book bookA = new Book("Book A");
        Publisher publisherA = new Publisher("Publisher A");

        BookPublisher bookPublisher = new BookPublisher();

        bookPublisher.setBook(bookA);
        bookPublisher.setPublisher(publisherA);

        bookPublisher.setPublishedDate(new Date());
        bookPublisher.setAffected(0);

        //  user.getUserMissions().add(userMission);
        bookA.getBookPublishers().add(bookPublisher);

        publisherRepository.save(publisherA);
        Book b = new Book();
        System.out.println("b****" +b);
        b = bookRepository.save(bookA);
        System.out.println("  b" + b.getId());
        System.out.println("  b" + b.getClass());

        //Book b2 =bookRepository.getOne(b.getId());
        Optional<Book> b2 =bookRepository.findById(b.getId());
        System.out.println("b2****" +b2.get().getName());

        System.out.println("bookA.getAffected()  " + bookA.getBookPublishers().get(0).getAffected());
        publisherRepository.findAll();
        System.out.println(publisherRepository.findAll().size());

        /****Test2***/
   /*     Publisher publisherB = new Publisher("Publisher B");

        BookPublisher bookPublisher2 = new BookPublisher();

        bookPublisher2.setBook(b);
        bookPublisher2.setPublisher(publisherB);
        bookPublisher2.setPublishedDate(new Date());
        bookPublisher2.setAffected(0);
        b.getBookPublishers().add(bookPublisher2);

        publisherRepository.save(publisherB);
        bookRepository.save(b);
        System.out.println("b****" + b.getBookPublishers().size());

        System.out.println("bookA.getAffected()  " + b.getBookPublishers().get(0).getAffected());
*/
    }
}
