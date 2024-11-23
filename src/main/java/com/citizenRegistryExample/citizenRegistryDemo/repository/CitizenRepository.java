package com.citizenRegistryExample.citizenRegistryDemo.repository;

import com.citizenRegistryExample.citizenRegistryDemo.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, String> {
    Optional<Citizen> findByAt(String at);
}