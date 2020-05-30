package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.evenement.repositories.EventRepositories;
import com.proxym.pfe.gestionAssociationBack.evenement.services.EvenementService;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepositories eventRepositories;

    @Override
    public User signinDao(String username) {

        System.out.println("Dao" + userRepository.findByUsername(username));
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameDao(String username) {
        return userRepository.findByUsername(username);
    }

   /* @Override
    public User findByEmailDao(String email) {
        return userRepository.findByEmail(email);
    }*/

    @Override
    public Boolean existsByUsernameDao(String username) {
        return userRepository.existsByUsername(username);
    }

    /*  @Override
      public Boolean existsByEmailDao(String email) {
          return userRepository.existsByEmail(email);
      }
  */
    @Override
    public User saveUserDao(User user) {
        user.setTauxEchange(calculTauxParticipation(user) * 100);
        return userRepository.save(user);
    }

    @Override
    public User saveUserDaoSinUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllDonneursDao() {
        return userRepository.findAllByUserBiensIsNotNull();
    }

    @Override
    public List<User> getAllBenevolesDao() {
        return userRepository.findAllByUserMissionsIsNotNull();
    }

    /******calcul taux de participaion**********/

    float calculTauxParticipation(User user) {

        List<Evenement> evenements = new ArrayList<>();
        //getEventByDesDons

        for (int i = 0; i < user.getUserMissions().size(); i++) {
            evenements.add(user.getUserMissions().get(i).getMission().getEvenement());
        }
        //getEventByDesMissions

        for (int i = 0; i < user.getUserBiens().size(); i++) {
            evenements.add(user.getUserBiens().get(i).getBien().getEvenement());
        }
        /*supprimer les doublons*/
        // Créer une liste de contenu unique basée sur les éléments de ArrayList
        Set<Evenement> mySet = new HashSet<>(evenements);

        // Créer une Nouvelle ArrayList à partir de Set
        List<Evenement> UserlistEvent = new ArrayList<>(mySet);
        List<Evenement> allListEvent = eventRepositories.findAll();
        System.out.println("UserlistEvent.size()  " + UserlistEvent.size());
        System.out.println("allListEvent.size()  " + allListEvent.size());

        return ((float) UserlistEvent.size() / (float) allListEvent.size());
    }
}
