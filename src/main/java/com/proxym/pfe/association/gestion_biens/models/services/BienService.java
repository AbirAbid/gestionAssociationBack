package com.proxym.pfe.association.gestion_biens.models.services;

import com.proxym.pfe.association.gestion_biens.models.dto.ParticiperBienFormDto;
import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;

import java.io.IOException;
import java.util.List;

public interface BienService {
    List<Bien> findAllByEventServiceRest(Long id) throws IOException;

    List<Bien> getListBien() throws IOException;

    void donnerBienSerice(ParticiperBienFormDto participerBienFormDto, String username);

     List<Bien> getBienByRegionRest(String ville) throws IOException;

    List<UserBien> getListBienByUserRest(String username) throws IOException;

    List<UserBien> getListUserBien();
}
