package com.Crimen.Asesino.Services.Impl;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Crimen.Asesino.Repository.RepositoryPersona;
import com.Crimen.Asesino.Repository.Entities.Persona;
import com.Crimen.Asesino.Services.PersonaService;

import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    
    private RepositoryPersona repositoryPersona;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll(){
        return (List<Persona>) repositoryPersona.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long id){
        return repositoryPersona.findById(id).orElse(null);

    }

    @Override
    public Persona save(Persona persona){
        return repositoryPersona.save(persona);
    }

    @Override
    public Persona update(Long id,Persona persona){
        Optional<Persona> personaCurrentOptional=repositoryPersona.findById(id);

        if(personaCurrentOptional.isPresent()){
            Persona personaCurrent = personaCurrentOptional.get();
            personaCurrent.setNombre(persona.getNombre());
            personaCurrent.setApellido(persona.getApellido());
            personaCurrent.setDireccion(persona.getDireccion());
            personaCurrent.setEmail(persona.getEmail());
            repositoryPersona.save(personaCurrent);
            return personaCurrent;

        }
        return null;
    }

    @Override
    public void delete(Long id){
        Optional<Persona> personaCurrent=repositoryPersona.findById(id);
        if(personaCurrent.isPresent()){
            repositoryPersona.delete(personaCurrent.get());
        }       
    }
    
    // se pone persona para poder instanciar la tabla
public HashMap<String,String> encontrarSospechoso(String dna, List<Persona> personas){
    int sospechoso, max = 0;
    Persona aux = null;
    double porcentaje;

    for(Persona p: personas){
        sospechoso = 0;
        for (int i=0; i < dna.length(); i++){
            if (dna.charAt(i) == p.getCromosoma().charAt(i)) {
                sospechoso++;
            }
        }
        if (sospechoso > max){
            max = sospechoso;
            aux = p;
        }
    }

    porcentaje = ((double)max / dna.length()) * 100; 
    HashMap<String,String> map = new HashMap<>();
    map.put("persona", aux.getNombre()+" "+aux.getApellido());
    map.put("porcentaje", Double.toString(porcentaje));
    return map;
}

    
    }

