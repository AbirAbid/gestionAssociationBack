package com.proxym.pfe.association.gestion_evenements.dto;

import lombok.Data;

@Data
public class EventParticipCount {
    private String titreEvt;
    private int nbParticipants;

    public EventParticipCount(String s, int i) {
        titreEvt = s;
        nbParticipants = i;
    }

}
