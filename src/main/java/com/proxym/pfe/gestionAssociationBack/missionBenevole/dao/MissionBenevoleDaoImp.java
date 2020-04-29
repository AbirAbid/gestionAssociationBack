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
            List<MissionBenevole> missionBenevolesList = missionBenevoles.stream().collect(Collectors.toList());

            /*for (int i = 0; i <= missionBenevolesList.size() - 1; i++) {
               /* if (!(missionBenevolesList.get(i).getTitre().isEmpty() ||
                        (missionBenevolesList.get(i).getDescription().isEmpty() && missionBenevolesList.get(i).getTitre().isEmpty())
                )) {*/
            // System.out.println("missionBenevolesList.get(" + i + " )" + missionBenevolesList.get(i));
            //  missionBenevoleRepositories.save(missionBenevolesList.get(i));
            //}

            // }
            missionBenevoleRepositories.saveAll(missionBenevoles.stream().collect(Collectors.toList()));


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public List<MissionBenevole> findAllMissionBenDao() {
        return missionBenevoleRepositories.findAll();
    }

    @Override
    public void deleteMissionDao(Long id) {
        // missionBenevoleRepositories.deleteById((long) 67);
        // missionBenevoleRepositories.deleteAll(missionBenevoleRepositories.findAllByEvenement_Id((long) 111));

        missionBenevoleRepositories.deleteAll(missionBenevoleRepositories.findAllByEvenement_Id(id));
    }

    @Override
    public List<MissionBenevole> findAllMissionByEventDao(Long id) {
        return missionBenevoleRepositories.findAllByEvenement_Id(id);
    }

    @Override
    public List<MissionBenevole> findAllByEvenement_VilleDao(String ville) {
        return missionBenevoleRepositories.findAllByEvenement_Ville(ville);
    }


}
