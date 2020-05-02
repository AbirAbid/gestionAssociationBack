package com.proxym.pfe.gestionAssociationBack.missionBenevole.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.ParticiperBienRepositories;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.ParticiperMissionRepositories;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticiperMissionBenevoleDaoImp implements ParticiperMissionBenevoleDao {
    @Autowired
    ParticiperMissionRepositories participerMissionRepositories;

    @Override
    public List<ParticiperMissionBenevole> findAllParticipationMissionDao() {
        return participerMissionRepositories.findAll();
    }

    @Override
    public List<ParticiperMissionBenevole> findAllByUserUsernameDao(String username) {
        return participerMissionRepositories.findAllByUser_Username(username);
    }

    @Override
    public ParticiperMissionBenevole saveParticipationMissionDao(ParticiperMissionBenevole participerMissionBenevole) {
        return participerMissionRepositories.save(participerMissionBenevole);
    }

    @Override
    public void deleteParticipationMissionDao(Long id) {
        participerMissionRepositories.deleteById(id);

    }

    @Override
    public Boolean existMissionUserDao(MissionBenevole missionBenevole, User user) {
        return participerMissionRepositories.existsParticiperMissionBenevoleByMissionBenevoleAndUser(missionBenevole, user);
    }

    @Override
    public ParticiperMissionBenevole findMissionAndUserDao(MissionBenevole missionBenevole, User user) {
        return participerMissionRepositories.findByMissionBenevoleAndUser(missionBenevole, user);
    }

    @Override
    public Boolean existParticipationEvenementDao(Evenement evenement) {
        return participerMissionRepositories.existsParticiperMissionBenevoleByMissionBenevole_Evenement(evenement);
    }
}
