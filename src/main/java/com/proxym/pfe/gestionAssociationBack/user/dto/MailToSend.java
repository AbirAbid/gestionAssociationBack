package com.proxym.pfe.gestionAssociationBack.user.dto;

import lombok.Data;

@Data
public class MailToSend {
    String receiver;
    String object;
    String textToSend;

}
