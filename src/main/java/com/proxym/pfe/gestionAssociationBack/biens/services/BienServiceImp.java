package com.proxym.pfe.gestionAssociationBack.biens.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienServiceImp implements BienService {
    @Autowired
    BienDao bienDao;

    @Override
    public List<Bien> findAllService() {
        try {
            return bienDao.findAllDao();
        } catch (Exception e) {

            System.out.println("exc Serv" + e);
            return null;
        }
    }

    @Override
    public void saveAllService(List<Bien> biens) {
        try {
            System.out.println("biensService" + biens);
            bienDao.saveAllDao(biens);
        } catch (Exception e) {
            System.out.println("exc Serv" + e);
        }

    }

    @Override
    public void editAllService(List<Bien> biens) {

    }

    @Override
    public List<Bien> findAllByEventService(Long id) {
        return bienDao.findAllByEventDao(id);
    }
}
