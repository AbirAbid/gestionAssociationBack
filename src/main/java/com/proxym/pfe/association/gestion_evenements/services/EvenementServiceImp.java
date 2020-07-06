package com.proxym.pfe.association.gestion_evenements.services;

import com.proxym.pfe.association.gestion_evenements.dto.EventParticipCount;
import com.proxym.pfe.association.gestion_biens.models.dao.BienDao;
import com.proxym.pfe.association.gestion_biens.models.entities.Bien;
import com.proxym.pfe.association.gestion_biens.models.entities.UserBien;
import com.proxym.pfe.association.gestion_evenements.dao.EvenementDao;
import com.proxym.pfe.association.gestion_evenements.dto.EvenementDto;
import com.proxym.pfe.association.gestion_evenements.dto.EventCountCategories;
import com.proxym.pfe.association.gestion_evenements.dto.EventCountElmtsDto;
import com.proxym.pfe.association.gestion_evenements.entities.Evenement;
import com.proxym.pfe.association.gestion_missions.dao.MissionDao;
import com.proxym.pfe.association.gestion_missions.entities.Mission;
import com.proxym.pfe.association.gestion_missions.entities.UserMission;
import com.proxym.pfe.association.gestion_sponsors.dao.SponsorDao;
import com.proxym.pfe.association.gestion_sponsors.entities.Sponsor;
import com.proxym.pfe.association.gestion_utilisateurs.dao.UserDao;
import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EvenementServiceImp implements EvenementService {

    @Autowired
    EvenementDao evenementDao;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BienDao bienDao;
    @Autowired
    MissionDao missionDao;
    @Autowired
    SponsorDao sponsorDao;
    @Autowired
    UserDao userDao;




    @Override
    public List<Evenement> listEventService() {
        System.out.println("***********we are in serv evt*********");

        return evenementDao.listEventDao();
    }

    @Override
    public void supprimerEvent(Long id) {
        bienDao.deleteBienDao(id);
        missionDao.deleteMissionDao(id);
        evenementDao.supprimerEventDao(id);

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
            eventCountElmtsDto.setNombreMembres(userDao.getAllMembreDao().size());

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
        List<User> users = userDao.getAllMembreDao();
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
    public List<EventCountCategories> countPartByCategorieEvent() {
        List<String> categorieEvent = new ArrayList<>();
        categorieEvent.add("aide-humanitaire");
        categorieEvent.add("aide-handicapes");
        categorieEvent.add("sante");
        categorieEvent.add("education");
        List<EventCountCategories> eventCountParticpCategories = new ArrayList<>();
        EventCountCategories eventCountCategories = new EventCountCategories();
        for (int i = 0; i < categorieEvent.size(); i++) {
            List<Evenement> evenements = evenementDao.findAllByCategorieDao(categorieEvent.get(i));
            for (int k = 0; k < evenements.size(); k++) {
                List<UserBien> userBiensEvent = getListDonneursByEvent(evenements.get(k));
                List<UserMission> userMissionsEvent = getListBenevolesByEvent(evenements.get(k));
                List<User> users = new ArrayList<>();

                for (int j = 0; j < userBiensEvent.size(); j++) {

                    users.add(userBiensEvent.get(j).getUser());

                }
                for (int j = 0; j < userMissionsEvent.size(); j++) {

                    users.add(userMissionsEvent.get(j).getUser());

                }
                eventCountCategories = new EventCountCategories(categorieEvent.get(i), users.size());


            }
            eventCountParticpCategories.add(eventCountCategories);
        }


        return eventCountParticpCategories;
    }

    @Override
    public List<EventParticipCount> countPartByEvent() {

        List<EventParticipCount> eventParticipCounts = new ArrayList<>();
        List<Evenement> evenements = evenementDao.listEventDao();

        for (int i = 0; i < evenements.size(); i++) {

            List<UserBien> userBiensEvent = getListDonneursByEvent(evenements.get(i));
            List<UserMission> userMissionsEvent = getListBenevolesByEvent(evenements.get(i));
            List<User> users = new ArrayList<>();

            for (int j = 0; j < userBiensEvent.size(); j++) {

                users.add(userBiensEvent.get(j).getUser());

            }
            for (int j = 0; j < userMissionsEvent.size(); j++) {

                users.add(userMissionsEvent.get(j).getUser());

            }
            /** pour éliminer redondance ***/
            Set<User> mySet1 = new HashSet<>(users);
            users = new ArrayList<>(mySet1);
            EventParticipCount eventParticipCount = new EventParticipCount(evenements.get(i).getTitre(), users.size());
            eventParticipCounts.add(eventParticipCount);
        }

        return eventParticipCounts;
    }

    @Override
    public void eventDetailService(Model model, Long id) throws ParseException {
        Evenement e = evenementDao.getEventDaoById(id);

        List<Bien> biens = bienDao.findAllByEventDao(id);
        List<Mission> missions = missionDao.findAllMissionByEventDao(id);
        List<UserBien> userBiensEvent = getListDonneursByEvent(e);
        List<UserMission> userMissionsEvent = getListBenevolesByEvent(e);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        String dateJourString = format.format(new Date());
        Date dateJour = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateJourString);


        model.addAttribute("dateJour", dateJour);
        model.addAttribute("evenement", e);
        model.addAttribute("biens", biens);
        model.addAttribute("userBiens", userBiensEvent);
        model.addAttribute("missions", missions);
        model.addAttribute("userMission", userMissionsEvent);
        model.addAttribute("nbbiens", biens.size());
        model.addAttribute("nbdonneurs", userBiensEvent.size());
        model.addAttribute("nbmissions", missions.size());
        model.addAttribute("nbbenevoles", userMissionsEvent.size());

    }


    /***********fonction get Event from EvenementDto********/
    Evenement eventFromEventDto(EvenementDto evenementDto) {


        //Evenement post = modelMapper.mapModels(evenementDto, Evenement.class);

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
