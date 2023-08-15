package com.medilabo.medilaboclient.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long id;
    @NotEmpty(message = "Le prenom du patient ne peut pas être vide.")
    private String prenom;
    @NotEmpty(message = "Le nom du patient ne peut pas être vide.")
    private String nom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La date de naissance du patient ne peut pas être vide")
    private LocalDate dateNaissance;
    @NotNull(message = "La Genre du patient ne peut pas être vide")
    private char genre;
    private String adressePostale;
    private String numero;

}
