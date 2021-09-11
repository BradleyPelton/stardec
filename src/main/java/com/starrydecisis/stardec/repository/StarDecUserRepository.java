package com.starrydecisis.stardec.repository;

import com.starrydecisis.stardec.model.securitymodels.StarDecUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StarDecUserRepository extends JpaRepository<StarDecUser, Integer> {
    Optional<StarDecUser> findByUserName(String userName);
}
