package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;


public interface UserDao {
    User findByUsernameDao(String username);
    Boolean existsByUsernameDao(String username);
    User saveUserDao(User user);
    User saveUserDaoSinUp(User user);
    List<User> getAllDonneursDao();
    List<User> getAllBenevolesDao();

    User getOneMembreDao(String id);
    List<User> getAllMembreDao();





}
