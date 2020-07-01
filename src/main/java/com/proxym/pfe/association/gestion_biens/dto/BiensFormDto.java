package com.proxym.pfe.association.gestion_biens.dto;

import com.proxym.pfe.association.gestion_biens.entities.Bien;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BiensFormDto {
    private List<Bien> biens;

    public BiensFormDto(List<Bien> biens) {
        this.biens = biens;
    }

    public BiensFormDto() {
        this.biens = new ArrayList<>();


    }


    public List<Bien> getBiens() {
        return biens;
    }

    public void setBiens(List<Bien> biens) {
        this.biens = biens;
    }

    public void addBien(Bien bien) {
        this.biens.add(bien);
    }
}
