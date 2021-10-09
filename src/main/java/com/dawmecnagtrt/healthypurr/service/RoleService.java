package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.Role.CreateRoleDto;
import com.dawmecnagtrt.healthypurr.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();
    Role getRole(Integer id);
    Role createRole(CreateRoleDto role) throws Exception;
    Role updateRole(CreateRoleDto role, Integer id) throws Exception;
    String deleteRole(Integer id);
}
