package com.proxym.pfe.gestionAssociationBack.bookPackage.Etudiant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByNom(String n);

    //pour selectionner page par page
    Page<Etudiant> findByNom(String n, Pageable pageable);

    //pour chercher

    @Query("select  e from Etudiant e  where e.nom like :x")
    Page<Etudiant> chercherEtudiant(@Param("x") String mc, Pageable pageable);

/*   @Query(value = "select e from Etudiant e where e.dateNaissance > :x and e.dateNaissance < :y")
    Page<Etudiant> chercherEtudiant(@Param("x") Date d1, @Param("y") Date d2);*/
}
