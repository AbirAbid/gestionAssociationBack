package com.proxym.pfe.gestionAssociationBack.sponsors.services;

import com.proxym.pfe.gestionAssociationBack.sponsors.dao.SponsorDao;
import com.proxym.pfe.gestionAssociationBack.sponsors.entities.Sponsor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Service
public class SponsorServiceImp implements SponsorService {

    @Autowired
    SponsorDao sponsorDao;
    @Value("${dir.images}")
    private String imageDir;

    @Override
    public void saveSponsorService(Sponsor s, MultipartFile file) {
        // pou n'est pas ecraser le meme nom
        try {

            if (!(file.isEmpty())) {
                s.setPhotoSponsor(file.getOriginalFilename());

            }
            sponsorDao.saveSponsorDao(s);

            if (!(file.isEmpty())) {
                file.transferTo(new File(imageDir + s.getIdSponsor()));

                //photo de la formulaire va etre stocker dans un fichier externe
                System.out.println("(file.isEmpty())" + file.isEmpty());
                System.out.println(" sponsor.getId()" + s.getIdSponsor());
                System.out.println("Sponsor" + s);


            }
        } catch (Exception e) {
        }
    }

    @Override
    public List<Sponsor> findAllSponsorServ() {
        return sponsorDao.findAllSponsorDao();
    }

    @Override
    public Page<Sponsor> findAllPageSponsorServ(PageRequest pageRequest) {
        return sponsorDao.findAllPageSponsorDao(pageRequest);
    }

    @Override
    public Page<Sponsor> rehercherPageSponsorSrv(String mc, PageRequest pageRequest) {
        return sponsorDao.rehercherPageSponsorDao(mc, pageRequest);
    }

    @Override
    public byte[] getPhotoService(Long id) throws Exception {
        File f = new File(imageDir + id);
        return IOUtils.toByteArray(new FileInputStream(f));
    }

    @Override
    public void supprimerSponsor(Long id) {
        sponsorDao.supprimerSponsor(id);
    }

    @Override
    public Sponsor getOneService(Long id) {
        return sponsorDao.getOneDao(id);
    }

    @Override
    public void updateSponsorService(Sponsor s) {
        sponsorDao.modifierSponsor(s);
    }


}
