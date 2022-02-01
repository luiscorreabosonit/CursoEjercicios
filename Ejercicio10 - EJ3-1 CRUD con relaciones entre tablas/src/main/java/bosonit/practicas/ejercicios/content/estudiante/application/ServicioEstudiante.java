package bosonit.practicas.ejercicios.content.estudiante.application;

import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.repository.jpa.EstudiantesRepository;
import bosonit.practicas.ejercicios.content.persona.application.ServicioPersona;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEstudiante {

    @Autowired
    EstudiantesRepository repository;

    @Autowired
    ServicioPersona servicioPersona;

    public Estudiante buscarEstudiante(String id){

        return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún estudiante con ese Id"));

    }

    public Estudiante crearEstudiante(EstudianteInputDTO estudianteInputDTO) {

        Persona persona = servicioPersona.buscarPorId(estudianteInputDTO.getPersona());

        if(repository.findByPersona(persona).isPresent()){

            throw new RuntimeException("La persona ya existe");

        }

        Estudiante estudiante = new Estudiante(estudianteInputDTO, persona);

        repository.save(estudiante);

        return estudiante;

    }

    public List<Estudiante> devolverEstudiantes(){

        return repository.findAll();

    }

}
