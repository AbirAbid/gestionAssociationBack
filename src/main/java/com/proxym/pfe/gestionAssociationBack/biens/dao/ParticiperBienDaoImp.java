package com.proxym.pfe.gestionAssociationBack.biens.dao;

import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import com.proxym.pfe.gestionAssociationBack.biens.repositories.ParticiperBienRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticiperBienDaoImp implements ParticiperBienDao {
    @Autowired
    ParticiperBienRepositories participerBienRepositories;

    @Override
    public List<ParticiperBien> findAllParticipationBienDao() {
        return null;
    }

    @Override
    public ParticiperBien saveParticipationBienDao(ParticiperBien participerBien) {
        return participerBienRepositories.save(participerBien);
    }



    @Override
    public void deleteParticipationBienDao(Long id) {
        participerBienRepositories.deleteById(id);

    }
}
