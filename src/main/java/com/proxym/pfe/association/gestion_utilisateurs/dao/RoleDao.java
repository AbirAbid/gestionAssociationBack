package com.proxym.pfe.association.gestion_utilisateurs.dao;

import com.proxym.pfe.association.gestion_utilisateurs.entities.Role;
import com.proxym.pfe.association.gestion_utilisateurs.entities.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Optional<Role> findByNameDao(RoleName nomRole);
    Optional<List<Role>>findByNameDaoList(RoleName nomRole);


}
