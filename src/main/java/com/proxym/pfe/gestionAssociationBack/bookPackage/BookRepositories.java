package com.proxym.pfe.gestionAssociationBack.bookPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<Book,Long> {
}
