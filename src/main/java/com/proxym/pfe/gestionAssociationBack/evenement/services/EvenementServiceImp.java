package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.sponsors.dao.SponsorDao;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class EvenementServiceImp implements EvenementService {

    @Autowired
    EvenementDao evenementDao;

    @Autowired
    BienDao bienDao;
    @Autowired
    MissionDao missionDao;
    @Autowired
    SponsorDao sponsorDao;


    @Override
    public Evenement addEventService(Evenement evenement) {
        return evenementDao.addEventDao(evenement);
    }

    @Override
    public List<Evenement> listEventService() {
        System.out.println("***********we are in serv evt*********");

        return evenementDao.listEventDao();
    }

    @Override
    public Evenement getOneEventByIdservice(Long id) {
        return evenementDao.getEventDaoById(id);
    }

    @Override
    public void supprimerEvent(Long id) {
        bienDao.deleteBienDao(id);
        missionDao.deleteMissionDao(id);
        evenementDao.supprimerEventDao(id);

    }

    @Override
    public Page<Evenement> findAllPageEvenementService(PageRequest pageRequest) {
        return evenementDao.findAllPageEvenementDao(pageRequest);
    }

    @Override
    public Page<Evenement> rehercherPageEvenementService(String mc, PageRequest pageRequest) {
        return evenementDao.rehercherPageEvenementDao(mc, pageRequest);
    }

    @Override
    public Optional<Evenement> findEventByIdService(Long id) {
        return evenementDao.findEventDaoById(id);
    }

    @Override
    public void AjouterEvent(@Valid EvenementDto evenementDto) {

        Evenement event = new Evenement();
    /*  event.setId(evenementDto.getId());

        event.setTitre(evenementDto.getTitre());
        event.setDescription(evenementDto.getDescription());
        event.setAdresse(evenementDto.getAdresse());
        event.setDateDebut(evenementDto.getDateDebut());
        event.setDateFin(evenementDto.getDateFin());
        event.setSponsors(evenementDto.getSponsors());
        event.setVille(evenementDto.getVille());
        event.setFrais(evenementDto.getFrais());
        event.setActive(evenementDto.getActive());
        event.setCategorie(evenementDto.getCategorie());

*/
        event.setFrais((double) 0);
        event = eventFromEventDto(evenementDto);
        Evenement e = evenementDao.addEventDao(event);
        /** for Affect  Sponsor ***/
        List<Sponsor> sponsors = event.getSponsors();

        for (int i = 0; i <= sponsors.size() - 1; i++) {
            Sponsor s = sponsors.get(i);
            s.setAffecte(1);
            sponsorDao.modifierSponsor(s);

        }
        /** End Champs event form1 **/


        for (int i = 0; i <= evenementDto.getBiens().size() - 1; i++) {

            evenementDto.getBiens().get(i).setEvenement(e);
            evenementDto.getBiens().get(i).setTotaleqteDonnee(0);
        }

        for (int i = 0; i <= evenementDto.getMissions().size() - 1; i++) {


            evenementDto.getMissions().get(i).setEvenement(e);

        }


        bienDao.saveAllDao(evenementDto.getBiens());
        missionDao.saveAllMissionDao(evenementDto.getMissions());

    }

    @Override
    public void ModifierEvent(@Valid EvenementDto evenementDto) {
        System.out.println("ModifierEvent::::::");
        Evenement event = new Evenement();
       /* event.setId(evenementDto.getId());

        event.setTitre(evenementDto.getTitre());
        event.setDescription(evenementDto.getDescription());
        event.setAdresse(evenementDto.getAdresse());

        event.setDateDebut(evenementDto.getDateDebut());

        event.setDateFin(evenementDto.getDateFin());

        event.setSponsors(evenementDto.getSponsors());
        event.setVille(evenementDto.getVille());
        event.setFrais(evenementDto.getFrais());
        event.setActive(evenementDto.getActive());*/
        event = eventFromEventDto(evenementDto);

        Evenement e = evenementDao.addEventDao(event);
        /** for Affect  Sponsor ***/
        List<Sponsor> sponsors = event.getSponsors();

        for (int i = 0; i <= sponsors.size() - 1; i++) {
            Sponsor s = sponsors.get(i);
            s.setAffecte(1);
            sponsorDao.modifierSponsor(s);

        }
        /** End Champs event form1 **/


        List<Bien> biens = bienDao.findAllByEventDao(e.getId());
        if (biens.size() > 0) {
            for (int i = 0; i <= biens.size() - 1; i++) {
                evenementDto.getBiens().get(i).setEvenement(e);
                evenementDto.getBiens().get(i).setTotaleqteDonnee(biens.get(i).getTotaleqteDonnee());

                evenementDto.getBiens().get(i).setId(biens.get(i).getId());
            }
            bienDao.saveAllDao(evenementDto.getBiens());
            if (evenementDto.getBiens().size() > biens.size()) {
                for (int i = biens.size(); i <= evenementDto.getBiens().size() - 1; i++) {
                    evenementDto.getBiens().get(i).setEvenement(e);
                    evenementDto.getBiens().get(i).setTotaleqteDonnee(0);
                }
                bienDao.saveAllDao(evenementDto.getBiens());

            }
        } else if (biens.size() == 0) {
            for (int i = 0; i <= evenementDto.getBiens().size() - 1; i++) {

                evenementDto.getBiens().get(i).setEvenement(e);
                evenementDto.getBiens().get(i).setTotaleqteDonnee(0);
            }
            bienDao.saveAllDao(evenementDto.getBiens());

        }
        List<Mission> missions = missionDao.findAllMissionByEventDao(e.getId());
        if (missions.size() > 0) {
            for (int i = 0; i <= missions.size() - 1; i++) {
                evenementDto.getMissions().get(i).setEvenement(e);

                evenementDto.getMissions().get(i).setId(missions.get(i).getId());
            }
            missionDao.saveAllMissionDao(evenementDto.getMissions());
            if (evenementDto.getMissions().size() > missions.size()) {
                for (int i = missions.size(); i <= evenementDto.getMissions().size() - 1; i++) {

                    evenementDto.getMissions().get(i).setEvenement(e);

                }
                missionDao.saveAllMissionDao(evenementDto.getMissions());

            }
        } else if (missions.size() == 0) {
            for (int i = 0; i <= evenementDto.getMissions().size() - 1; i++) {


                evenementDto.getMissions().get(i).setEvenement(e);

            }

            missionDao.saveAllMissionDao(evenementDto.getMissions());


        }

    }

    @Override
    public EvenementDto formulaireUpdate(Long id) {
        System.out.println(" formulaireUpdate:::::");

        Evenement e = evenementDao.getEventDaoById(id);
        //   System.out.println(" Evenement:::::" + e);

        EvenementDto evenementDto = new EvenementDto();

        // System.out.println(" e.getSponsors()    " + e.getSponsors());

        evenementDto.affectToEventDto(e);
        // System.out.println(" evenementDto().getSponsors()    " + evenementDto.getSponsors());

        List<Bien> biens = bienDao.findAllByEventDao(id);
        List<Mission> missions = missionDao.findAllMissionByEventDao(id);
        //  System.out.println("******biens.isEmpty()*****" + biens.isEmpty());
        if (biens.size() != 0) {
            for (int i = 0; i <= biens.size() - 1; i++) {
                evenementDto.addBien(biens.get(i));
            }
        } else {

            evenementDto.addBien(new Bien());

        }
        if (missions.size() != 0) {
            for (int i = 0; i <= missions.size() - 1; i++) {
                evenementDto.addMission(missions.get(i));
                //System.out.println("missionBenevoles.get(i)*******" + missions.get(i).getId());


            }
        } else {

            evenementDto.addMission(new Mission());

        }

        System.out.println(" evenementDto.getDateDebut():::::" + evenementDto.getDateDebut());

        return evenementDto;
    }

    Evenement eventFromEventDto(EvenementDto evenementDto) {
        Evenement event = new Evenement();
        event.setId(evenementDto.getId());

        event.setTitre(evenementDto.getTitre());
        event.setDescription(evenementDto.getDescription());
        event.setAdresse(evenementDto.getAdresse());

        event.setDateDebut(evenementDto.getDateDebut());

        event.setDateFin(evenementDto.getDateFin());

        event.setSponsors(evenementDto.getSponsors());
        event.setVille(evenementDto.getVille());
        event.setFrais(evenementDto.getFrais());
        event.setActive(evenementDto.getActive());
        event.setCategorie(evenementDto.getCategorie());

        return event;

    }
}
