package com.proxym.pfe.gestionAssociationBack.sponsors.dao;

import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.sponsors.repositories.SponsorRepository;
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
    public Page<Sponsor> findAllPageSponsorDao(PageRequest pageRequest) {
        return sponsorRepository.findAll(pageRequest);
    }

    @Override
    public Page<Sponsor> rehercherPageSponsorDao(String mc, PageRequest pageRequest) {
        return sponsorRepository.chercherSponsor(mc, pageRequest);
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
