package com.proxym.pfe.gestionAssociationBack.user.services;


import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//UserDetailsService est utilisée pour récupérer les données liées à l’utilisateur
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    //loadUserByUsername qui trouve une entité utilisateur
    // basée sur username
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
       User user= userDao.findByUsernameDao(username);
        System.out.println("************Service UserDetailsServiceImpl ***********"+ user);
        return UserPrinciple.build(user);
    }
}
