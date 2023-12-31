package com.onlineshop.repository;

import com.onlineshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findFirstByLogin(String login);
    User findFirstByUuid(String uuid);
}
