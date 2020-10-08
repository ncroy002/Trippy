package com.trippy.back.repos;

import com.trippy.back.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <Account, Long> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    Account findByEmail(String email);
    Boolean existsByEmail(String email);
    }


