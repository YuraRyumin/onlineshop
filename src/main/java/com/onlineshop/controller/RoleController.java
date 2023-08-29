package com.onlineshop.controller;

import com.onlineshop.domain.Role;
import com.onlineshop.dto.RoleDTO;
import com.onlineshop.service.RoleService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roleslist")
    public Iterable<RoleDTO> listOfRoles(){
        return roleService.getAllRoles();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createrole")
    public void createRole(@RequestBody Role role){
        roleService.saveRole(role);
    }

    @PostMapping("/saverole")
    public void saveRole(@RequestBody Role role){
        Role thisRole = roleService.getRoleEntityByName(role.getName());
        //roleService.saveRole(thisRole);
    }
}
