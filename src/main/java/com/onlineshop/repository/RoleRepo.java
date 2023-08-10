package com.onlineshop.repository;

import com.onlineshop.domain.Role;
import com.onlineshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findFirstById(Long id);
    Role findFirstByName(String name);
}
