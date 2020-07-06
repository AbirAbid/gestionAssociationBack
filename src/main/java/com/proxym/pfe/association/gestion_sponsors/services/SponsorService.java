package com.proxym.pfe.association.gestion_sponsors.services;

import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SponsorService {
    void saveSponsorService(Sponsor s, MultipartFile file);

    List<Sponsor> findAllSponsorServ();


    byte[] getPhotoService(Long id) throws Exception;

    void supprimerSponsor(Long id);

    Sponsor getOneService(Long id);


}
