package com.proxym.pfe.gestionAssociationBack.user.dao;

import com.proxym.pfe.gestionAssociationBack.user.entities.Role;
import com.proxym.pfe.gestionAssociationBack.user.entities.RoleName;
import com.proxym.pfe.gestionAssociationBack.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleDaoImp implements RoleDao {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByNameDao(RoleName nomRole) {
        return roleRepository.findByName(nomRole);
    }

    @Override
    public Optional<List<Role>> findByNameDaoList(RoleName nomRole) {
        return roleRepository.findAllByName(nomRole);
    }
}
