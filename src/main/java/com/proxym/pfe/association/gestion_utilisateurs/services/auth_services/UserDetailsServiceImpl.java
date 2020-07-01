package com.proxym.pfe.association.gestion_utilisateurs.services.auth_services;


import com.proxym.pfe.association.gestion_utilisateurs.dao.RoleDao;
import com.proxym.pfe.association.gestion_utilisateurs.dao.UserDao;
import com.proxym.pfe.association.gestion_utilisateurs.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//UserDetailsService est utilisée pour récupérer les données liées à l’utilisateur
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
   /* @Override
    @Transactional
    //loadUserByUsername qui trouve une entité utilisateur
    // basée sur username
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
       User user= userDao.findByUsernameDao(username);
        System.out.println("************Service UserDetailsServiceImpl ***********"+ user);
        return UserPrinciple.build(user);
    }*/


    @Override
    @Transactional
//UserDetails interface de spring security
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        com.proxym.pfe.association.gestion_utilisateurs.entities.User appUser = this.userDao.findByUsernameDao(userName);
        System.out.println("userName:::::" + userName);
        //  System.out.println("Found User: " + appUser.getNom());

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser.getNom());
        System.out.println("appUser.getRoles()::::: " + appUser.getRoles().size());

        Set<Role> list = appUser.getRoles();

        // [ROLE_USER, ROLE_ADMIN,..]


        List<Role> aList = new ArrayList<>();
        aList.addAll(list);
        System.out.println("aList.get(0).getName() ::::: " + aList.get(0).getName());


        List<Role> roles = roleDao.findByNameDaoList(aList.get(0).getName()).get();
        System.out.println("roles.size()::::" + roles.size());
        List<String> roleNamesString = new ArrayList<>();
       // roleNamesString.add(roles.get(0).getName().name());



        for (int i = 0; i <= roles.size() - 1; i++) {
            System.out.println("inside for:::::" + i);
           roleNamesString.add(roles.get(i).getName().name());

        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNamesString != null) {
            System.out.println("roleNamesString != null:::::" + roleNamesString != null);

            for (String role : roleNamesString) {
                System.out.println("role:::::" + role);
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), //
                appUser.getPassword(), grantList);
        System.out.println("userDetails:::" + userDetails);
        return userDetails;
    }

}
