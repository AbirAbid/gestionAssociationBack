package com.proxym.pfe.association.gestion_sponsors.dao;

import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import com.proxym.pfe.association.gestion_sponsors.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorDaoImp implements SponsorDao {
    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public void saveSponsorDao(Sponsor s) {
        sponsorRepository.save(s);

    }

    @Override
    public List<Sponsor> findAllSponsorDao() {
        return sponsorRepository.findAll();
    }




    @Override
    public void supprimerSponsor(Long id) {
        sponsorRepository.deleteById(id);

    }

    @Override
    public void modifierSponsor(Sponsor sp) {
        sponsorRepository.save(sp);

    }

    @Override
    public Sponsor getOneDao(Long id) {
        return sponsorRepository.getOne(id);
    }


}
