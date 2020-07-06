package com.proxym.pfe.association.gestion_utilisateurs.dao;

import com.proxym.pfe.association.gestion_utilisateurs.entities.User;

import java.util.List;


public interface UserDao {
    User findByUsernameDao(String username);
    Boolean existsByUsernameDao(String username);
    User saveUserDao(User user);
    User saveUserDaoSinUp(User user);
    List<User> getAllDonneursDao();
    List<User> getAllBenevolesDao();
    List<User> getAllMembreDao();





}
