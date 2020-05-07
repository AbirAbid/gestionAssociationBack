package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User signinDao(String username) {

        System.out.println("Dao" + userRepository.findByUsername(username));
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameDao(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmailDao(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByUsernameDao(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmailDao(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUserDao(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllDonneursDao() {
        return userRepository.findAllByUserBiensIsNotNull();
    }


}
