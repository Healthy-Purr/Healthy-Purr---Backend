package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.entity.Role;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.RoleRepository;
import com.dawmecnagtrt.healthypurr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(Integer id) {
        return roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Role with id: " + id +" not found"));
    }

    @Override
    @Transactional
    public Role createRole(Role role) throws Exception {
        if(roleRepository.existsByRoleName(role.getRoleName())){
            throw new Exception("Role already in use");
        }
        role.setState("CREATED");
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role, Integer id) throws Exception {
        Optional<Role> roleDB = roleRepository.findById(id);
        if(!roleDB.isPresent()){
            throw new EntityNotFoundException("Role with id: " + id +" not found");
        }
        if(roleRepository.existsByRoleName(role.getRoleName()) && !Objects.equals(roleDB.get().getRoleName(), role.getRoleName())){
            throw new Exception("Role already in use");
        }
        Role roleUpdated = roleDB.get();
        roleUpdated.setRoleName(role.getRoleName());
        roleUpdated.setRoleDescription(role.getRoleDescription());
        roleUpdated.setState("UPDATED");
        return roleRepository.save(roleUpdated);
    }

    @Override
    @Transactional
    public String deleteRole(Integer id) {
        Optional<Role> roleDB = roleRepository.findById(id);
        if(!roleDB.isPresent()){
            throw new EntityNotFoundException("Role with id: " + id +" not found");
        }
        Role role = roleDB.get();
        role.setState("DELETED");
        roleRepository.save(role);
        return "DELETED Role with id: " + id;
    }
}

