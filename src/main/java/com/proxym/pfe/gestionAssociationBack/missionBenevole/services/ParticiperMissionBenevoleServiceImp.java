package com.proxym.pfe.gestionAssociationBack.missionBenevole.services;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.ParticiperMissionBenevoleDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.ParticiperMissionBenevole;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticiperMissionBenevoleServiceImp implements ParticiperMissionBenevoleService {
    @Autowired
    ParticiperMissionBenevoleDao participerMissionBenevoleDao;

    @Override
    public List<ParticiperMissionBenevole> findAllParticipationMissionService() {
        return participerMissionBenevoleDao.findAllParticipationMissionDao();
    }

    @Override
    public List<ParticiperMissionBenevole> findAllByUserUsernameService(String username) {
        return participerMissionBenevoleDao.findAllByUserUsernameDao(username);
    }

    @Override
    public ParticiperMissionBenevole saveParticipationMissionService(ParticiperMissionBenevole participerMissionBenevole) {
        return participerMissionBenevoleDao.saveParticipationMissionDao(participerMissionBenevole);
    }

    @Override
    public void deleteParticipationMissionService(Long id) {
        participerMissionBenevoleDao.deleteParticipationMissionDao(id);
    }

    @Override
    public Boolean existMissionUserService(MissionBenevole missionBenevole, User user) {
        return participerMissionBenevoleDao.existMissionUserDao(missionBenevole, user);
    }

    @Override
    public ParticiperMissionBenevole findMissionAndUserService(MissionBenevole missionBenevole, User user) {
        return participerMissionBenevoleDao.findMissionAndUserDao(missionBenevole, user);
    }

    @Override
    public Boolean existParticipationEvenementService(Evenement evenement) {
        return participerMissionBenevoleDao.existParticipationEvenementDao(evenement);
    }
}
