package com.citizenRegistryExample.citizenRegistryDemo.controller;

import com.citizenRegistryExample.citizenRegistryDemo.model.Citizen;
import com.citizenRegistryExample.citizenRegistryDemo.service.CitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/citizens")
@Validated
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping
    public ResponseEntity<Citizen> registerCitizen(@Valid @RequestBody Citizen citizen) {
        return ResponseEntity.ok(citizenService.registerCitizen(citizen));
    }

    @DeleteMapping("/{at}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable String at) {
        citizenService.deleteCitizen(at);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{at}")
    public ResponseEntity<Citizen> updateCitizen(
            @PathVariable String at,
            @RequestParam(required = false) String socialSecurityNumber,
            @RequestParam(required = false) String homeAddress) {
        return ResponseEntity.ok(citizenService.updateCitizen(at, socialSecurityNumber, homeAddress));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Citizen>> searchCitizens(Citizen searchCriteria) {
        return ResponseEntity.ok(citizenService.searchCitizens(searchCriteria));
    }


    @GetMapping("/{at}")
    public ResponseEntity<Citizen> getCitizen(@PathVariable String at) {
        return ResponseEntity.ok(citizenService.getCitizen(at));
    }
}