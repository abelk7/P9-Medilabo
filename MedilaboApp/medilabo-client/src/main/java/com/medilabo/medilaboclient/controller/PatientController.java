package com.medilabo.medilaboclient.controller;

import com.medilabo.medilaboclient.entity.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${cust.param.uri.patient}")
    private String uriPatient;

    @GetMapping({"/", "/patients", "/list"})
    public ModelAndView index(ModelMap model) {
        Patient[] reponse = restTemplate.getForObject(uriPatient, Patient[].class);
        List<Patient> patientList = Arrays.asList(reponse);
        model.addAttribute("listPatients", patientList);
        return new ModelAndView("list", model);
    }

    @GetMapping(value = "/detailPatient/{id}")
    public ModelAndView detailPatient(ModelMap model, @PathVariable long id) {
        Patient patient = restTemplate.getForObject(uriPatient+"/"+ id, Patient.class);
        model.addAttribute("patient", patient);
        return new ModelAndView("detail-patient", model);
    }

    @GetMapping(value = "/postPatient")
    public ModelAndView showFormPostPatient(ModelMap model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return new ModelAndView("post-patient", model);
    }

    @GetMapping(value = "/updatePatient/{id}")
    public ModelAndView showFormUpdatePatient(ModelMap model, @PathVariable long id) {
        Patient patient = restTemplate.getForObject(uriPatient+"/"+ id, Patient.class);
        model.addAttribute("patient", patient);
        return new ModelAndView("update-patient", model);
    }

    @PostMapping(value = "/savePatient")
    public ModelAndView updatePatient(@Valid Patient patientNewValues, BindingResult result, ModelMap model) {
        if(patientNewValues.getId() == null) {
            model.addAttribute("message", "Un nouveau patient à été ajouté");
            if(result.hasErrors()) {
                model.addAttribute("patient", patientNewValues);
                return new ModelAndView("post-patient", model);
            }
        } else {
            model.addAttribute("message", "Le patient à été mis à jour");
            if(result.hasErrors()) {
                model.addAttribute("patient", patientNewValues);
                return new ModelAndView("update-patient", model);
            }
        }

        restTemplate.postForEntity(uriPatient, patientNewValues, Patient.class);
        return new ModelAndView("redirect:/list", model);
    }

    @GetMapping(value = "/deletePatient/{id}")
    public ModelAndView deletePatient(@PathVariable long id, ModelMap model) {
        restTemplate.delete(uriPatient+"/" + id);
        model.addAttribute("message", "Le patient à été supprimé");
        return new ModelAndView("redirect:/list", model);
    }
}
