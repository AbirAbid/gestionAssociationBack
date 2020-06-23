package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;

import java.io.IOException;
import java.util.List;

public interface BienService {

    List<Bien> findAllBienService();

    void saveAllService(List<Bien> biens);

    List<Bien>  findAllByEventService(Long id);
    List<Bien> findAllByEventServiceRest(Long id) throws IOException;

    List<Bien> findAllByEvenement_VilleService(String ville);

    Bien saveBienService(Bien bien);

    List<Bien> getListBien() throws IOException;

    void donnerBienSerice(ParticiperBienFormDto participerBienFormDto, String username);

     List<Bien> getBienByRegionRest(String ville) throws IOException;

    List<UserBien> getListBienByUserRest(String username) throws IOException;

    List<UserBien> getListUserBien();

}
