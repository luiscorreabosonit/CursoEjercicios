package bosonit.practicas.ejercicios.servicios;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.modelos.PersonaDTO;
import bosonit.practicas.ejercicios.repositorios.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioREST {

    @Autowired
    PersonasRepository personasRepository;

    public void crearPersona(PersonaDTO personaDTO){

        if (personaDTO.getUsuario()==null) throw new RuntimeException("Usuario no puede ser nulo");

        if (personaDTO.getUsuario().length()>10 || personaDTO.getUsuario().length() <6) throw new RuntimeException("La longitud de usuario debe ser entre 6 y 10 caracteres");

        if (personaDTO.getPassword()==null) throw new RuntimeException("Password no puede ser nulo");

        if (personaDTO.getName()==null) throw new RuntimeException("Name no puede ser nulo");

        if (personaDTO.getCompanyName()==null) throw new RuntimeException("CompanyName no puede ser nulo");

        if (personaDTO.getPersonalEmail()==null) throw new RuntimeException("PersonalEmail no puede ser nulo");

        if (personaDTO.getCity()==null) throw new RuntimeException("City no puede ser nulo");

        if (personaDTO.getActive()==null) throw new RuntimeException("Active no puede ser nulo");

        Persona persona = new Persona(personaDTO.getUsuario(), personaDTO.getPassword(), personaDTO.getName(), personaDTO.getSurname(),
                                  personaDTO.getCompanyName(), personaDTO.getPersonalEmail(), personaDTO.getCity(),
                                  personaDTO.getActive(), personaDTO.getImagenUrl());

        personasRepository.save(persona);

    }

    public Persona buscarPorId(int id){

        return personasRepository.findById(id).orElseThrow(() -> new RuntimeException("No se han encontrado personas con el id: " + id));

    }

    public List<Persona> buscarPorNombre(String nombre){

        return personasRepository.buscarPorNombre(nombre).orElseThrow(() -> new RuntimeException("No se han encontrado personas con el nombre: " + nombre));

    }

    public List<Persona> devolverPersonas(){

        return personasRepository.findAll();

    }

}
