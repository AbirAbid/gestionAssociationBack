package com.proxym.pfe.gestionAssociationBack.missionBenevole.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.repositories.MissionBenevoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionBenevoleDaoImp implements MissionBenevoleDao {
    @Autowired
    MissionBenevoleRepositories missionBenevoleRepositories;

    @Override
    public void saveAllMissionBenDao(List<MissionBenevole> missionBenevoles) {

        try {
            System.out.println("missionBenevolesDao" + missionBenevoles);
            List<MissionBenevole> missionBenevolesList = missionBenevoles.stream().collect(Collectors.toList());
            System.out.println("missionBenevolesDaoToList" + missionBenevolesList);
            for (int i = 0; i <= missionBenevolesList.size() - 1; i++) {
                if (!missionBenevolesList.get(i).getTitre().isEmpty()) {
                    missionBenevoleRepositories.save(missionBenevolesList.get(i));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public List<MissionBenevole> findAllMissionBenDao() {
        return missionBenevoleRepositories.findAll();
    }
}
