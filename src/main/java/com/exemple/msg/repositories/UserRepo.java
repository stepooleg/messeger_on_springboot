package com.exemple.msg.repositories;

import com.exemple.msg.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
