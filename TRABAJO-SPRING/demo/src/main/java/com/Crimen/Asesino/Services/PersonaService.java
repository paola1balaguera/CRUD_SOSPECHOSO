package com.Crimen.Asesino.Services;

import java.util.HashMap;
import java.util.List;

import com.Crimen.Asesino.Repository.Entities.Persona;

public interface PersonaService {


    List<Persona> findAll();

    Persona findById(Long id);

    Persona save(Persona persona);

    Persona update(Long id,Persona persona);

    void delete(Long id);

    public HashMap<String,String> encontrarSospechoso(String dna, List<Persona> personas);

} 
