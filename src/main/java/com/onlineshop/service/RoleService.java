package com.onlineshop.service;

import com.onlineshop.domain.Role;
import com.onlineshop.dto.RoleDTO;
import com.onlineshop.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public RoleDTO getEmptyDTO(){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("");
        return roleDTO;
    }

    public RoleDTO convertEntityToDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName().trim());
        return roleDTO;
    }

    public Iterable<RoleDTO> convertAllEntitysToDTO(Iterable<Role> roles){
        return StreamSupport.stream(roles.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public RoleDTO getRoleByName(String name){
        if(name.isEmpty()){
            return getEmptyDTO();
        }
        return convertEntityToDTO(roleRepo.findFirstByName(name));
    }

    public Role getRoleEntityByName(String name){
        return roleRepo.findFirstByName(name);
    }

    public Iterable<RoleDTO> getAllRoles(){
        return convertAllEntitysToDTO(roleRepo.findAll());
    }

    @Transactional
    public void saveRole(Role role){
        roleRepo.save(role);
    }

    @Transactional
    public void saveRole(String name){
        Role role = new Role(name);
        roleRepo.save(role);
    }
}
