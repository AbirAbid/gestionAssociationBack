package com.proxym.pfe.association.gestion_utilisateurs.repositories;

import com.proxym.pfe.association.gestion_utilisateurs.entities.User;
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

    Boolean existsByUsername(String username);

    List<User> findAllByUserBiensIsNotNull();
    List<User> findAllByUserMissionsIsNotNull();

    @Query("select  u from User u  where  u.isAdmin = 0")
    List<User> getMembreList();




}
