package com.proxym.pfe.gestionAssociationBack.bookPackage.manytomany.repositories;

import com.proxym.pfe.gestionAssociationBack.bookPackage.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
