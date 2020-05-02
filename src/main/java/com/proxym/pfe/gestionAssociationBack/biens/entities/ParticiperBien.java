package com.proxym.pfe.gestionAssociationBack.biens.entities;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "participer_bien")

public class ParticiperBien {


    @Id
    @GeneratedValue

    private Long idP;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)

    private User user;

    @ManyToOne
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    private Integer qteDonnee;
    private Date dateParticipation;


}
