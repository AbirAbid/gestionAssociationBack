package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Optional<Role> findByNameDao(RoleName nomRole);
    Optional<List<Role> >findByNameDaoList(RoleName nomRole);


}
