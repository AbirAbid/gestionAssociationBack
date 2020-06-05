package com.proxym.pfe.gestionAssociationBack.user.repositories;

import com.proxym.pfe.gestionAssociationBack.biens.entities.Bien;
import com.proxym.pfe.gestionAssociationBack.biens.entities.UserBien;
import com.proxym.pfe.gestionAssociationBack.cotisations.entities.Cotisation;
import com.proxym.pfe.gestionAssociationBack.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    User findByUsername(String username);

   // User findByEmail(String username);

    Boolean existsByUsername(String username);

   // Boolean existsByEmail(String email);

    /*@Query("select  u from User u  where u.nom like :x")
    Page<User> chercherUser(@Param("x") String mc, Pageable pageable);*/
    @Query("select  u from User u  where u.nom like :x and u.isAdmin = 0")
    Page<User> chercherUser(@Param("x") String mc, Pageable pageable);

    List<User> findAllByUserBiensIsNotNull();
    List<User> findAllByUserMissionsIsNotNull();

    @Query("select  u from User u  where  u.isAdmin = 0")
    List<User> getMembreList();
   // Long countAllById();




}
