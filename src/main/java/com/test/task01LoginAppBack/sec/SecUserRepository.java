package com.test.task01LoginAppBack.sec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long> {
    Optional<SecUser> findByUsername(String userName);
}
