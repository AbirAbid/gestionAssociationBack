package com.proxym.pfe.gestionAssociationBack.evenement.services;

import com.proxym.pfe.gestionAssociationBack.biens.dao.BienDao;
import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.evenement.dao.EvenementDao;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EvenementDto;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountCategories;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventCountElmtsDto;
import com.proxym.pfe.gestionAssociationBack.evenement.dto.EventParticipCount;
import com.proxym.pfe.gestionAssociationBack.evenement.entities.Evenement;
import com.proxym.pfe.gestionAssociationBack.membre.dao.MembreDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.dao.MissionDao;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.Mission;
import com.proxym.pfe.gestionAssociationBack.missionBenevole.entities.UserMission;
import com.proxym.pfe.gestionAssociationBack.sponsors.dao.SponsorDao;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import com.proxym.pfe.gestionAssociationBack.user.dao.UserDao;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
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
    @Autowired
    MembreDao membreDao;
    @Autowired
    UserDao userDao;


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
        Evenement event = eventFromEventDto(evenementDto);

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
    public EventCountElmtsDto getElementNumber() {
        try {
            EventCountElmtsDto eventCountElmtsDto = new EventCountElmtsDto();
            eventCountElmtsDto.setNombreEvents(evenementDao.listEventDao().size());
            eventCountElmtsDto.setNombreMissions(missionDao.findAllMissionDao().size());
            eventCountElmtsDto.setNombreSponsors(sponsorDao.findAllSponsorDao().size());
            eventCountElmtsDto.setNombreMembres(membreDao.getAllMembreDao().size());

            return eventCountElmtsDto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<Evenement> findAllByCategorieService(String categorie) {
        return evenementDao.findAllByCategorieDao(categorie);
    }

    @Override
    public List<UserBien> getListDonneursByEvent(Evenement evenement) {
        /*************** pour avoir liste donneurs************************/
        List<User> users = userDao.getAllDonneursDao();
        List<UserBien> userBiens = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getUserBiens().size(); j++) {
                userBiens.add(users.get(i).getUserBiens().get(j));
            }
        }
        List<UserBien> userBiensEvent = new ArrayList<>();
        for (int i = 0; i < userBiens.size(); i++) {
            if (userBiens.get(i).getBien().getEvenement() == evenement) {
                userBiensEvent.add(userBiens.get(i));
            }
        }
        /** pour éliminer redondance ***/
        Set<UserBien> mySet = new HashSet<>(userBiensEvent);
        userBiensEvent = new ArrayList<>(mySet);
        return userBiensEvent;
    }

    @Override
    public List<UserMission> getListBenevolesByEvent(Evenement evenement) {

        List<User> usersBenevole = userDao.getAllBenevolesDao();
        List<UserMission> userMissions = new ArrayList<>();

        for (int i = 0; i < usersBenevole.size(); i++) {
            for (int j = 0; j < usersBenevole.get(i).getUserMissions().size(); j++) {

                userMissions.add(usersBenevole.get(i).getUserMissions().get(j));

            }

        }

        List<UserMission> userMissionsEvent = new ArrayList<>();
        for (int i = 0; i < userMissions.size(); i++) {
            if (userMissions.get(i).getMission().getEvenement() == evenement) {
                userMissionsEvent.add(userMissions.get(i));
            }
        }
        /** pour éliminer redondance ***/
        Set<UserMission> mySet1 = new HashSet<>(userMissionsEvent);
        userMissionsEvent = new ArrayList<>(mySet1);

        return userMissionsEvent;
    }

    @Override
    public EvenementDto formulaireUpdate(Long id) {
        Evenement e = evenementDao.getEventDaoById(id);
        EvenementDto evenementDto = new EvenementDto();
        evenementDto.affectToEventDto(e);
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

    @Override
    public void TauxEchangeForAllUser() {
        List<User> users = membreDao.getAllMembreDao();
        for (int i = 0; i < users.size(); i++) {
            userDao.saveUserDao(users.get(i));
        }
    }

    @Override
    public List<EventCountCategories> countCategEvent() {

        List<EventCountCategories> eventCountCategories = new ArrayList<>();
        List<Evenement> evenements = evenementDao.listEventDao();
        eventCountCategories.add(new EventCountCategories("aide-humanitaire", 0));
        eventCountCategories.add(new EventCountCategories("aide-handicapes", 0));
        eventCountCategories.add(new EventCountCategories("sante", 0));
        eventCountCategories.add(new EventCountCategories("education", 0));
        for (int i = 0; i <= evenements.size() - 1; i++) {
            for (int j = 0; j <= eventCountCategories.size() - 1; j++) {
                if (evenements.get(i).getCategorie().equals(eventCountCategories.get(j).getCategorie())) {
                    eventCountCategories.get(j).setCount(eventCountCategories.get(j).getCount() + 1);
                }

            }
        }


        return eventCountCategories;

    }

    @Override
    public List<EventParticipCount> countPartByEvent() {
        List<EventParticipCount> eventParticipCounts = new ArrayList<>();

        return eventParticipCounts;
    }


    /***********fonction get Event from EvenementDto********/
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
