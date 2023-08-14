package com.onlineshop.service;

import com.onlineshop.domain.User;
import com.onlineshop.dto.RoleDTO;
import com.onlineshop.dto.UserDTO;
import com.onlineshop.repository.RoleRepo;
import com.onlineshop.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public UserDTO getEmptyDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("");
        userDTO.setRole("");
        userDTO.setPhone("");
        userDTO.setEmail("");
        userDTO.setUuid("");
        userDTO.setActivationCode("");
        userDTO.setActive(false);
        return userDTO;
    }

    public UserDTO convertEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRole().getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setUuid(user.getUuid());
        userDTO.setActivationCode(user.getActivationCode());
        userDTO.setActive(user.isActive());
        return userDTO;
    }

    public Iterable<UserDTO> convertAllEntitysToDTO(Iterable<User> users){
        return StreamSupport.stream(users.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void saveUser(User user){
        userRepo.save(user);
    }

    @Transactional
    public void saveUser(){
        User user = new User();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findFirstByLogin(username);
    }
}
