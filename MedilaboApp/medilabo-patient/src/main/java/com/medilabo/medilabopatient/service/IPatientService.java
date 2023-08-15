package com.medilabo.medilabopatient.service;

import com.medilabo.medilabopatient.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> findAll();
    Optional<Patient> findById(Long id);
    Patient save(Patient patient);
    void delete(Long id);
}
