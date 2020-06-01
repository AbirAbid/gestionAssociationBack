package com.proxym.pfe.gestionAssociationBack.user.repositories;

import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);

    Optional<List<Role>> findAllByName(RoleName nomRole);
}
