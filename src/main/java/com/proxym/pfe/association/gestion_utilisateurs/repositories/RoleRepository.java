package com.proxym.pfe.association.gestion_utilisateurs.repositories;

import com.proxym.pfe.association.gestion_utilisateurs.entities.Role;
import com.proxym.pfe.association.gestion_utilisateurs.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);

    Optional<List<Role>> findAllByName(RoleName nomRole);
}
