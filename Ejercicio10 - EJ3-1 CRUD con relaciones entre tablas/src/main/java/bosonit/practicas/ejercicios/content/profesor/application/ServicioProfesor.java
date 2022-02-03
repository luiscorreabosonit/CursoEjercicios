package bosonit.practicas.ejercicios.content.profesor.application;

import bosonit.practicas.ejercicios.content.enums.Rama;
import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.repository.jpa.EstudiantesRepository;
import bosonit.practicas.ejercicios.content.persona.application.ServicioPersona;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.repository.jpa.ProfesoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProfesor {

    @Autowired
    ProfesoresRepository repository;

    @Autowired
    ServicioPersona servicioPersona;

    public Profesor buscarProfesor(String id){

        return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún profesor con ese Id"));

    }

    public Profesor crearProfesor(ProfesorInputDTO profesorInputDTO) {

        Persona persona = servicioPersona.buscarPorId(profesorInputDTO.getPersona());

        if(repository.findByPersona(persona).isPresent()){

            throw new RuntimeException("La persona ya existe");

        }

        Profesor profesor = new Profesor(profesorInputDTO, persona);

        repository.save(profesor);

        return profesor;

    }

    public Profesor editarProfesor(ProfesorInputDTO profesorInputDTO, String id){

        Profesor profesor = repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún profesor con ese Id"));

        if(profesorInputDTO.getRama()!=null) profesor.setRama(Rama.valueOf(profesorInputDTO.getRama()));
        if(profesorInputDTO.getComentarios()!=null) profesor.setComentarios(profesorInputDTO.getComentarios());
        if(profesorInputDTO.getPersona()!=0){

            Persona persona = servicioPersona.buscarPorId(profesorInputDTO.getPersona());

            if(repository.findByPersona(persona).isPresent()){

                throw new RuntimeException("La persona ya existe");

            }

            profesor.setPersona(persona);

        }

        repository.save(profesor);

        return profesor;

    }

    public void eliminarProfesor(String idProfesor){

        repository.findById(idProfesor).orElseThrow(() -> new RuntimeException("El profesor con ID "+idProfesor+" no se ha encontrado"));

        repository.deleteById(idProfesor);

    }

    public List<Profesor> devolverProfesores(){

        return repository.findAll();

    }

    public void añadirAlumno(Profesor profesor, Estudiante alumno){

        profesor.getAlumnos().add(alumno);

        repository.save(profesor);

    }

}
