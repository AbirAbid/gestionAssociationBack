package com.proxym.pfe.gestionAssociationBack.user.services;

import com.proxym.pfe.gestionAssociationBack.user.dto.MailToSend;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.SignUpForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface UserService {
    ResponseEntity<?> signup(SignUpForm signUpForm);
    User findUserByUsernameService(String username);

    List<User> getAllMembreService();

    User getOneMembreService(String id);

    void sendMailMembre(MailToSend mailToSend) throws MessagingException;

}
