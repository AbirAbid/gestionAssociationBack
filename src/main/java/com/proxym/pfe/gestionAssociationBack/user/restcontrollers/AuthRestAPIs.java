package com.proxym.pfe.gestionAssociationBack.user.restcontrollers;

import com.proxym.pfe.gestionAssociationBack.user.dto.request.LoginForm;
import com.proxym.pfe.gestionAssociationBack.user.dto.response.JwtResponse;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import com.proxym.pfe.gestionAssociationBack.user.security.jwt.JwtProvider;
import com.proxym.pfe.gestionAssociationBack.user.service.UserService;
import com.proxym.pfe.gestionAssociationBack.user.dto.request.SignUpForm;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class AuthRestAPIs {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        try {
            System.out.println("*****************AuthRestAPIs-signin*****************");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
            return null;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        try {
            System.out.println("***************************AuthRestAPIs-signup***************************");
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


}

