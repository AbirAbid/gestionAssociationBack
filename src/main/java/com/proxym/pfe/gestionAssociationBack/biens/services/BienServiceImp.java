package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BienServiceImp implements BienService {
    @Autowired
    BienDao bienDao;
    @Autowired
    UserDao userDao;
    @Autowired
    EvenementDao evenementDao;


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
        return  bienDao.findAllByEventDao(id);
    }

    @Override
    public List<Bien> findAllByEventServiceRest(Long id) throws IOException {
        List<Bien> biens = bienDao.findAllByEventDao(id);

        return ConvertListBienToJson(biens);

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

        List<Bien> biens = bienDao.findAllDao();


        return ConvertListBienToJson(biens);
    }

    @Override
    public void donnerBienSerice(ParticiperBienFormDto participerBienFormDto, String username) {
        User user = userDao.findByUsernameDao(username);
        Bien bien = participerBienFormDto.getBien();
        UserBien userBien = new UserBien();
        int qteDonnee = participerBienFormDto.getQteDonnee();
        System.out.println("qteDonnee****" + qteDonnee);

        List<UserBien> userBiens = user.getUserBiens();
        List<Long> bienUserId = new ArrayList<>();

        for (int i = 0; i < userBiens.size(); i++) {
            bienUserId.add(userBiens.get(i).getBien().getId());
            System.out.println(userBiens.get(i).getBien());
        }
        /** IF  exist USER AND BIEN***/
        if (bienUserId.contains(bien.getId())) {
            int index = bienUserId.indexOf(bien.getId());
            System.out.println("index****" + index);
            user.getUserBiens().get(index).setQteDonnee(user.getUserBiens().get(index).getQteDonnee() + qteDonnee);
            bien.setTotaleqteDonnee(bien.getTotaleqteDonnee() + qteDonnee);
            bienDao.saveBienDao(bien);
            userDao.saveUserDao(user);
        } else {
            bien.getEvenement().setActive(1);
            userBien.setUser(user);
            userBien.setBien(bien);
            userBien.setQteDonnee(5);
            userBien.setDateParticipation(new Date());
            user.getUserBiens().add(userBien);
            bien.setTotaleqteDonnee(bien.getTotaleqteDonnee() + qteDonnee);
            evenementDao.addEventDao(bien.getEvenement());
            bienDao.saveBienDao(bien);
            userDao.saveUserDao(user);


            System.out.println("***** user.getUserBiens().get(0).getAffected()********" + user.getUserBiens().get(0).getQteDonnee());
        }
    }

    @Override
    public List<Bien> getBienByRegionRest(String ville) throws IOException {

        List<Bien> biens = bienDao.findAllByEvenement_VilleDao(ville);
        return ConvertListBienToJson(biens);
    }

    @Override
    public List<UserBien> getListBienByUserRest(String username) throws IOException {
        User user = userDao.findByUsernameDao(username);
        List<UserBien> userBiens = user.getUserBiens();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;

        List<UserBien> userBiens1 = new ArrayList<>();
        for (int i = 0; i < userBiens.size(); i++) {
            jsonInString = mapper.writeValueAsString(userBiens.get(i).getBien());

            //get Bien sous forme json
            UserBien userBien = mapper.readValue(mapper.writeValueAsString(userBiens.get(i)), UserBien.class);

            userBiens1.add(userBien);
        }
        return userBiens1;

    }

    @Override
    public List<UserBien> getListUserBienRest() {
        List<User> users = userDao.getAllDonneursDao();
        List<UserBien> userBiens = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                userBiens.add(users.get(i).getUserBiens().get(j));
            }
        }
        return userBiens;
    }


    public List<Bien> ConvertListBienToJson(List<Bien> biens) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

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
