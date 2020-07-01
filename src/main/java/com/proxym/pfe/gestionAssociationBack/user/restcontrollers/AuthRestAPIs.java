package com.proxym.pfe.gestionAssociationBack.user.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.user.dao.RoleDao;
import com.proxym.pfe.gestionAssociationBack.user.dto.UpdateProfileForm;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.LoginForm;
import com.proxym.pfe.gestionAssociationBack.user.dto.response.JwtResponse;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.repositories.UserRepository;
import com.proxym.pfe.gestionAssociationBack.user.security.jwt.JwtProvider;
import com.proxym.pfe.gestionAssociationBack.user.services.UserService;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.SignUpForm;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class AuthRestAPIs {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleDao roleDao;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        try {
            System.out.println("*****************AuthRestAPIs-signin*****************");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), userService.findUserByUsernameService(userDetails.getUsername())));
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        try {
            System.out.println("************signUpRequest*****" + signUpRequest);
            return userService.signup(signUpRequest);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getUser")
    public User getUser(@Valid @RequestBody String username) {
        try {
            System.out.println("**************AuthRestAPIs-getUser*****************");
            System.out.println("customerService.getMembre(username)" + userService.findUserByUsernameService(username));
            return userService.findUserByUsernameService(username);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/apiMembreAuthoriser/getUser/{username}")
    public User getUserByUsername(@PathVariable String username) {
        try {
            System.out.println("**************AuthRestAPIs-getUser*****************");
            System.out.println("userService.findUserByUsernameService(username)" + userService.findUserByUsernameService(username));
            return userService.findUserByUsernameService(username);
        } catch (Exception e) {
            return null;
        }


    }

    @PostMapping(value = "/apiMembreAuthoriser/updateProfile")
    public User updateprofile(@Valid @RequestBody UpdateProfileForm updateProfileForm) {

        try {

            User user = userRepository.getOne(updateProfileForm.getId());


            user.setNom(updateProfileForm.getNom());
            user.setTelephone(updateProfileForm.getTelephone());
            user.setPrenom(updateProfileForm.getPrenom());
            user.setOccupation(updateProfileForm.getOccupation());
            user.setUsername(updateProfileForm.getUsername());
            user.setGouvernoratRes(updateProfileForm.getGouvernoratRes());
            user.setGenre(updateProfileForm.getGenre());
            user.setDateNaissance(updateProfileForm.getDateNaissance());


            return userRepository.saveAndFlush(user);
        } catch (Exception e) {

            return null;
        }

    }


}

