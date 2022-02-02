package bosonit.practicas.ejercicios.content.estudiante.application;

import bosonit.practicas.ejercicios.content.enums.Rama;
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

}
