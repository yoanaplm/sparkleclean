package com.sparkleclean.sparkleclean.user.repository;

import com.sparkleclean.sparkleclean.user.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername (String username);

    boolean existsByUsername (String username);

    boolean existsByEmail (String email);
}
