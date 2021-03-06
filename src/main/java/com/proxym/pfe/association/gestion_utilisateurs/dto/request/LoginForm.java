package com.proxym.pfe.association.gestion_utilisateurs.dto.request;


import lombok.*;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
public class LoginForm {
    @NotBlank
    @Size(min = 3, max = 60)
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
