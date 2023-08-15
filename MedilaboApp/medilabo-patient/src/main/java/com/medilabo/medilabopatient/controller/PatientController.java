package com.medilabo.medilabopatient.controller;

import com.medilabo.medilabopatient.exception.PatientNotFoundException;
import com.medilabo.medilabopatient.exception.UnableToAddNewPatientException;
import com.medilabo.medilabopatient.model.Patient;
import com.medilabo.medilabopatient.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/patients")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping(value = "/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Patient getPatient(@PathVariable long id) {
        Optional<Patient> patient = patientService.findById(id);
        if(!patient.isPresent()) {
            throw new PatientNotFoundException("Le patient avec l'id " + id + "n'existe pas");
        }
        return patient.get();
    }

    @PostMapping(value = "/patients")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Patient postPatient(@RequestBody Patient patient) {
        Patient nouveauPatient = patientService.save(patient);
        if(nouveauPatient == null) {
            throw new UnableToAddNewPatientException("Impossible d'ajouter un nouveau patient");
        }
        return nouveauPatient;
    }

    @PutMapping(value = "/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Patient updatePatient(@PathVariable long id, @RequestBody Patient patientNewValues) {
        Optional<Patient> patientFound = patientService.findById(id);
        if(patientFound.isEmpty()) {
            throw new PatientNotFoundException("Le patient avec l'id " + id + "n'existe pas");
        }
        Patient patientToUpdate = patientFound.get();
        patientToUpdate.setNom(patientNewValues.getNom());
        patientToUpdate.setPrenom(patientNewValues.getPrenom());
        patientToUpdate.setGenre(patientNewValues.getGenre());
        patientToUpdate.setNumero(patientNewValues.getNumero());
        patientToUpdate.setDateNaissance(patientNewValues.getDateNaissance());
        patientToUpdate.setAdressePostale(patientNewValues.getAdressePostale());

        patientService.save(patientToUpdate);

        return patientToUpdate;
    }

    @DeleteMapping(value = "/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deletePatient(@PathVariable long id) {
        Optional<Patient> patientFound = patientService.findById(id);
        if(patientFound.isEmpty()) {
            throw new PatientNotFoundException("Le patient avec l'id " + id + "n'existe pas");
        }
        patientService.delete(id);
    }
}
