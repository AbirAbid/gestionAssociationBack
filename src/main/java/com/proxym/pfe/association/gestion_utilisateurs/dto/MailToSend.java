package com.proxym.pfe.association.gestion_utilisateurs.dto;

import lombok.Data;

@Data
public class MailToSend {
    String receiver;
    String object;
    String textToSend;

}
