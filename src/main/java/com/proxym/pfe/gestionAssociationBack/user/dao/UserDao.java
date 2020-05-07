package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;

import java.util.List;


public interface UserDao {
    /*User findByUsername(String username);

    User findByEmail(String username);
    //Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);*/
    User signinDao(String username);

    User findByUsernameDao(String username);

    User findByEmailDao(String username);

    Boolean existsByUsernameDao(String username);

    Boolean existsByEmailDao(String email);

    User saveUserDao(User user);
    List<User> getAllDonneursDao();


}
