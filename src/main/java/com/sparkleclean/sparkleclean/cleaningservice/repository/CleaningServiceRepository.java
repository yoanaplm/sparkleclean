package com.sparkleclean.sparkleclean.cleaningservice.repository;

import com.sparkleclean.sparkleclean.cleaningservice.model.CleaningService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CleaningServiceRepository extends JpaRepository<CleaningService, UUID> {

Optional<CleaningService> findByName(String name);
}
