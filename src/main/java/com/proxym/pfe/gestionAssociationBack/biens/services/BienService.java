package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.dto.ParticiperBienFormDto;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.bookPackage.Book;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BienService {

    List<Bien> findAllService();

    void saveAllService(List<Bien> biens);

    void editAllService(List<Bien> biens);
    List<Bien>  findAllByEventService(Long id);
    List<Bien> findAllByEventServiceRest(Long id) throws IOException;

    List<Bien> findAllByEvenement_VilleService(String ville);

    Bien saveBienService(Bien bien);

    List<Bien> getListBien() throws IOException;

    void donnerBienSerice(ParticiperBienFormDto participerBienFormDto, String username);

    public List<Bien> getBienByRegionRest(String ville) throws IOException;

    List<UserBien> getListBienByUserRest(String username) throws IOException;

    List<UserBien> getListUserBienRest();

}
