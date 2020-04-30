package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.BienRepositories;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.repositories.EventRepositories;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.MissionBenevole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BienDaoImp implements BienDao {
    @Autowired
    BienRepositories bienRepositories;

    /*  @Autowired
      EventRepositories eventRepositories;

      @Override
      public List<Bien> findAllByEventIdDao(Long id) {
          List<Bien> biensWithId = new ArrayList<Bien>();
          List<Bien> biens = bienRepositories.findAll();

          for (int i = 1; i <= biens.size()-1; i++) {
              if (biens.get(i).getEvenement() == eventRepositories.getOne(id)) {
                  biensWithId.add(biens.get(i));
              }

          }
          return biensWithId;

      }
  */
    @Override
    public List<Bien> findAllDao() {
        try {
            return bienRepositories.findAll();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Bien saveBienDao(Bien bien) {
        return bienRepositories.save(bien);
    }

    @Override
    public void saveAllDao(List<Bien> biens) {
        try {

            System.out.println("biensDao" + biens);
            List<Bien> bienList = biens.stream().collect(Collectors.toList());
            System.out.println("bienList" + bienList);
           /* for (int i = 0; i <= bienList.size() - 1; i++) {
             //   if (!bienList.get(i).getTitreBien().isEmpty()) {

                    bienRepositories.save(bienList.get(i));
                //}
            }*/
            bienRepositories.saveAll(biens.stream().collect(Collectors.toList()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void editAllDao(List<Bien> biens) {

    }

    @Override
    public List<Bien> findAllByEventDao(Long id) {
        System.out.println("bienRepositories.findAllByEvenement_Id(id)*****" +
                bienRepositories.findAllByEvenement_Id(id));
        return bienRepositories.findAllByEvenement_Id(id);
    }

    @Override
    public List<Bien> findAllByEvenement_VilleDao(String ville) {
        return bienRepositories.findAllByEvenement_Ville(ville);
    }

    @Override
    public void deleteBienDao(Long id) {
        // bienRepositories.delete(bienRepositories.getOne((long) 88));
        // bienRepositories.deleteAll(bienRepositories.findAllByEvenement_Id((long) 111));
        bienRepositories.deleteAll(bienRepositories.findAllByEvenement_Id(id));
    }
}
