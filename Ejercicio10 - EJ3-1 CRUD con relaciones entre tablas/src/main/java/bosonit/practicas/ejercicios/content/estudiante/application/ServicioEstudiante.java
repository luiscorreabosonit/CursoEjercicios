package bosonit.practicas.ejercicios.content.estudiante.application;

import bosonit.practicas.ejercicios.content.enums.Rama;
import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.repository.jpa.EstudiantesRepository;
import bosonit.practicas.ejercicios.content.estudio.application.ServicioEstudio;
import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.application.ServicioPersona;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.application.ServicioProfesor;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ServicioEstudiante {

    @Autowired
    EstudiantesRepository repository;

    @Autowired
    ServicioPersona servicioPersona;

    @Autowired
    ServicioProfesor servicioProfesor;

    @Autowired
    ServicioEstudio servicioEstudio;

    public Estudiante buscarEstudiante(String id){

        return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún estudiante con ese Id"));

    }

    public Estudiante crearEstudiante(EstudianteInputDTO estudianteInputDTO) {

        Persona persona = servicioPersona.buscarPorId(estudianteInputDTO.getPersona());

        if(repository.findByPersona(persona).isPresent()){

            throw new RuntimeException("La persona ya existe");

        }

        Profesor profesor = servicioProfesor.buscarProfesor(estudianteInputDTO.getProfesor());

        Estudiante estudiante = new Estudiante(estudianteInputDTO, persona, profesor);

        repository.save(estudiante);

        servicioProfesor.añadirAlumno(profesor, estudiante);

        Estudiante estudianteGuardado = repository.getById(estudiante.getId_estudiante());

        return estudianteGuardado;

    }

    public Estudiante editarEstudiante(EstudianteInputDTO estudianteInputDTO, String id){

        Estudiante estudiante = repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún estudiante con ese Id"));

        if(estudianteInputDTO.getRama()!=null) estudiante.setRama(Rama.valueOf(estudianteInputDTO.getRama()));
        if(estudianteInputDTO.getComentarios()!=null) estudiante.setComentarios(estudianteInputDTO.getComentarios());
        if(estudianteInputDTO.getNum_hours_week()!=0) estudiante.setNum_hours_week(estudianteInputDTO.getNum_hours_week());
        if(estudianteInputDTO.getPersona()!=0){

            Persona persona = servicioPersona.buscarPorId(estudianteInputDTO.getPersona());

            if(repository.findByPersona(persona).isPresent()){

                throw new RuntimeException("La persona ya existe");

            }

            estudiante.setPersona(persona);

        }

        repository.save(estudiante);

        return estudiante;

    }

    public void eliminarEstudiante(String idEstudiante){

        repository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("El estudiante con ID "+idEstudiante+" no se ha encontrado"));

        repository.deleteById(idEstudiante);

    }

    public List<Estudiante> devolverEstudiantes(){

        return repository.findAll();

    }

    public Estudiante asignarAsignaturasAEstudiante(String idEstudiante, EstudianteInputDTO estudianteInputDTO){

        Estudiante estudiante = repository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("No se ha encontrado al estudiante: "+idEstudiante));

        List<Estudio> estudios = estudianteInputDTO.getEstudios().stream().map(estudio -> servicioEstudio.buscarEstudio(estudio)).collect(Collectors.toList());

        estudios.stream().forEach(estudio -> {
            if(estudiante.getEstudios().contains(estudio))
                throw new RuntimeException("El estudiante ya tiene el estudio con ID: "+estudio.getId_estudio());
            estudiante.getEstudios().add(estudio);
        });

        Estudiante estudianteGuardado = repository.save(estudiante);

        return estudianteGuardado ;

    }

    public Estudiante desasignarAsignaturasAEstudiante(String idEstudiante, EstudianteInputDTO estudianteInputDTO){

        Estudiante estudiante = repository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("No se ha encontrado al estudiante: "+idEstudiante));

        List<Estudio> estudios = estudianteInputDTO.getEstudios().stream().map(estudio -> servicioEstudio.buscarEstudio(estudio)).collect(Collectors.toList());

        estudios.stream().forEach(estudio -> {
            if(!estudiante.getEstudios().contains(estudio))
                throw new RuntimeException("El estudiante no tiene el estudio con ID: "+estudio.getId_estudio());
            estudiante.getEstudios().remove(estudio);
        });

        Estudiante estudianteGuardado = repository.save(estudiante);

        return estudianteGuardado ;

    }

}
