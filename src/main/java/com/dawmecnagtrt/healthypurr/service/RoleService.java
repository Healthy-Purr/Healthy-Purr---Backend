package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();
    Role getRole(Integer id);
    Role createRole(Role role) throws Exception;
    Role updateRole(Role role, Integer id) throws Exception;
    String deleteRole(Integer id);
}
