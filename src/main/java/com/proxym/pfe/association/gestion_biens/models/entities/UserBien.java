package com.proxym.pfe.association.gestion_biens.models.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
// @JsonIdentityInfo est utilisé pour indiquer que l'identité de l'objet sera utilisée pendant la sérialisation / dé-sérialisation
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//sérializer un objet consiste à le convertir en un tableau d'octets,
// que l'on peut ensuite écrire dans un fichier, envoyer sur un réseau au travers d'une socket(interface de connexion)
public class UserBien implements Serializable  {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "bien_id")
    private Bien bien;

    private int qteDonnee;
    private Date dateParticipation;

    public UserBien() {
    }

    public int getQteDonnee() {
        return qteDonnee;
    }

    public void setQteDonnee(int qteDonnee) {
        this.qteDonnee = qteDonnee;
    }

    public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = dateParticipation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
