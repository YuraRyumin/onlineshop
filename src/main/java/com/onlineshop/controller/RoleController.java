package com.onlineshop.controller;

import com.onlineshop.domain.Role;
import com.onlineshop.dto.RoleDTO;
import com.onlineshop.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public Iterable<RoleDTO> listOfRoles(){
        return roleService.getAllRoles();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createrole")
    public void createRole(@RequestBody Role role){
        roleService.saveRole(role);
    }
}
