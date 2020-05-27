package com.proxym.pfe.gestionAssociationBack.membre.entities;

import lombok.Data;

@Data
public class MailToSend {
    String receiver;
    String object;
    String textToSend;

}
