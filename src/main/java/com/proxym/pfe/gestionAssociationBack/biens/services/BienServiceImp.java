package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BienServiceImp implements BienService {
    @Autowired
    BienDao bienDao;

    @Override
    public List<Bien> findAllService() {
        try {
            return bienDao.findAllDao();
        } catch (Exception e) {

            System.out.println("exc Serv" + e);
            return null;
        }
    }

    @Override
    public void saveAllService(List<Bien> biens) {
        try {
            System.out.println("biensService" + biens);
            bienDao.saveAllDao(biens);
        } catch (Exception e) {
            System.out.println("exc Serv" + e);
        }

    }

    @Override
    public void editAllService(List<Bien> biens) {

    }

    @Override
    public List<Bien> findAllByEventService(Long id) {
        return bienDao.findAllByEventDao(id);
    }

    @Override
    public List<Bien> findAllByEvenement_VilleService(String ville) {
        return bienDao.findAllByEvenement_VilleDao(ville);
    }

    @Override
    public Bien saveBienService(Bien bien) {
        return bienDao.saveBienDao(bien);
    }

    @Override
    public List<Bien> getListBien() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Bien> biens = bienDao.findAllDao();
        List<Bien> biens1 = new ArrayList<>();
        String jsonInString;

        for (int i = 0; i < biens.size(); i++) {
            jsonInString = mapper.writeValueAsString(biens.get(i));
            System.out.println("jsonInString" + jsonInString);
            Bien bien = mapper.readValue(jsonInString, Bien.class);

            System.out.println(mapper.writeValueAsString(biens.get(i).getId()));
            biens1.add(bien);
        }

        return biens1;
    }
}
