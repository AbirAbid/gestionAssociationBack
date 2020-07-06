package com.proxym.pfe.association.gestion_biens.models.dao;

import com.proxym.pfe.association.gestion_biens.models.repositories.BienRepositories;
import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BienDaoImp implements BienDao {
    @Autowired
    BienRepositories bienRepositories;

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
            List<Bien> bienListToSave = new ArrayList<>();
            // System.out.println("bienList" + bienList);

           /* for (int i = 0; i <= bienList.size() - 1; i++) {

                if (!bienList.get(i).getTitreBien().isEmpty()) {



                } }
               */
           bienRepositories.saveAll(biens.stream().collect(Collectors.toList()));


        } catch (Exception e) {
            System.out.println(e);
        }

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
        bienRepositories.deleteAll(bienRepositories.findAllByEvenement_Id(id));
    }
}
