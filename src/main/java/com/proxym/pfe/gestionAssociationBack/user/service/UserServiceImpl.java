package com.proxym.pfe.gestionAssociationBack.user.service;

import com.proxym.pfe.gestionAssociationBack.user.dao.RoleDao;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.dto.response.ResponseMessage;
import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.RoleName;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.security.jwt.JwtProvider;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;


    @Override
    public ResponseEntity<?> signup(SignUpForm signUpRequest) {
        System.out.println("****************signup***************");
        try {
            if (userDao.existsByUsernameDao(signUpRequest.getUsername())) {
                return new ResponseEntity<>(new ResponseMessage("Probléme -> Ce nom d'utilisateur existe déjà"),
                        HttpStatus.BAD_REQUEST);
            }

            if (userDao.existsByEmailDao(signUpRequest.getEmail())) {
                return new ResponseEntity<String>("Probléme -> Ce mail d'utilisateur existe déjà", HttpStatus.BAD_REQUEST);
            }

            // Creating user's account
            User user = new User(signUpRequest.getNom(),
                    signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getPrenom(),
                    signUpRequest.getDateNaissance(),
                    signUpRequest.getTelephone(),
                    signUpRequest.getGouvernoratRes(),
                    signUpRequest.getOccupation(),
                    signUpRequest.getGenre()
            );

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
            System.out.println(strRoles + "***" + signUpRequest.getEmail() + "****" + signUpRequest.getUsername() + "*****");

            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminMembreRole = roleDao.findByNameDao(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                        roles.add(adminMembreRole);

                        break;

                    default:
                        Role ActeurCivileRole = roleDao.findByNameDao(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                        roles.add(ActeurCivileRole);
                }
            });
            user.setRoles(roles);
            userDao.saveUserDao(user);

            return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);

        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public SignUpForm findUserByEmail(String email) {
        return null;
    }

    @Override
    public SignUpForm changePassword(SignUpForm signUpForm, String newPassword) {
        return null;
    }

    @Override
    public List<User> getAllDonneursService() {
        return userDao.getAllDonneursDao();
    }

    @Override
    public List<User> getAllBenevolesService() {
        return userDao.getAllBenevolesDao();
    }

    @Override
    public User findUserByUsernameService(String username) {
        System.out.println("************UserServiceImpl-getUser*****************");
        return userDao.findByUsernameDao(username);
    }

    @Override
    public SignUpForm updateProfile(SignUpForm signUpForm) {
        return null;
    }
}


