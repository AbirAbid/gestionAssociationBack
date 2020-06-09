package com.proxym.pfe.gestionAssociationBack.evenement.dto;

import lombok.Data;

@Data
public class EventCountCategories {
    private String categorie;
    private int count;

    public EventCountCategories(String s, int i) {
        categorie=s;
        count=i;
    }
}
