package com.proxym.pfe.association.gestion_evenements.dto;

import lombok.Data;

@Data
public class EventCountCategories {
    private String categorie;
    private int count;

    public EventCountCategories(String s, int i) {
        categorie=s;
        count=i;
    }
    public EventCountCategories() {

    }
}
