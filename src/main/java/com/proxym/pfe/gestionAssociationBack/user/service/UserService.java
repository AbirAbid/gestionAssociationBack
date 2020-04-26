package com.proxym.pfe.gestionAssociationBack.user.service;

import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.SignUpForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> signup(SignUpForm signUpForm);


    User getUser(String username);


    SignUpForm findUserByEmail(String email);

    SignUpForm updateProfile(SignUpForm signUpForm);


    SignUpForm changePassword(SignUpForm signUpForm, String newPassword);

}
