package com.proxym.pfe.gestionAssociationBack.user.services;

import com.proxym.pfe.gestionAssociationBack.user.dao.RoleDao;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceRestImp implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user= userDao.findByUsernameDao(username);
        System.out.println("************Service UserDetailsServiceImpl ***********"+ user);

       // User user = userRepository.findByUsername(username);

        return UserPrinciple.build(user);
    }
}
