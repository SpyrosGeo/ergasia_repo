package com.citizenRegistryExample.citizenRegistryDemo.service;

import com.citizenRegistryExample.citizenRegistryDemo.model.Citizen;
import com.citizenRegistryExample.citizenRegistryDemo.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    public Citizen registerCitizen(Citizen citizen) {
        Optional<Citizen> existing = citizenRepository.findByAt(citizen.getAt());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Citizen with AT already exists");
        }
        return citizenRepository.save(citizen);
    }

    public void deleteCitizen(String at) {
        if (!citizenRepository.existsById(at)) {
            throw new IllegalArgumentException("Citizen with this AT does not exist");
        }
        citizenRepository.deleteById(at);
    }

    public Citizen updateCitizen(String at, String socialSecurityNumber, String homeAddress) {
        Citizen citizen = citizenRepository.findById(at).orElseThrow(() -> new IllegalArgumentException("Citizen not found"));
        if (socialSecurityNumber != null) citizen.setSocialSecurityNumber(socialSecurityNumber);
        if (homeAddress != null) citizen.setHomeAddress(homeAddress);
        return citizenRepository.save(citizen);
    }

    public List<Citizen> searchCitizens(Citizen searchCriteria) {
        // Implement custom search logic or use JpaSpecificationExecutor to support field-based searching.
        return citizenRepository.findAll();
    }

    public Citizen getCitizen(String at) {
        return citizenRepository.findById(at).orElseThrow(() -> new IllegalArgumentException("Citizen not found"));
    }
}
