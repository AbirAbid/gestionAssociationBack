package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.ParticiperBienDao;
import com.proxym.pfe.gestionAssociationBack.biens.entities.ParticiperBien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticiperBienServiceImp implements ParticiperBienService {
    @Autowired
    ParticiperBienDao participerBienDao;

    @Override
    public List<ParticiperBien> findAllByUser_UsernameService(String username) {
        return participerBienDao.findAllByUser_UsernameDao(username);
    }

    @Override
    public List<ParticiperBien> findAllParticipationBienService() {
        return participerBienDao.findAllParticipationBienDao();
    }

    @Override
    public ParticiperBien saveParticipationBienService(ParticiperBien participerBien) {
        return participerBienDao.saveParticipationBienDao(participerBien);
    }

    @Override
    public void deleteParticipationBienService(Long id) {
        participerBienDao.deleteParticipationBienDao(id);
    }
}
