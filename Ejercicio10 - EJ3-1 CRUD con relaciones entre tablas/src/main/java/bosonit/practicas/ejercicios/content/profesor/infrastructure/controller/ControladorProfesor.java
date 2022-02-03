package bosonit.practicas.ejercicios.content.profesor.infrastructure.controller;

import bosonit.practicas.ejercicios.content.estudiante.application.ServicioEstudiante;
import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import bosonit.practicas.ejercicios.content.profesor.application.ServicioProfesor;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
@Slf4j
public class ControladorProfesor {

    @Autowired
    ServicioProfesor servicioProfesor;

    @GetMapping("/{id}")
    ResponseEntity buscarProfesor(@PathVariable String id, @RequestParam(defaultValue = "simple") String outputType){

        log.info("Buscando profesor con id: "+id);

        try {

            switch (outputType.toLowerCase()) {

                case "simple": {

                    Profesor profesor = servicioProfesor.buscarProfesor(id);

                    ProfesorOutputDTO profesorOutputDTO = profesor.aProfesorDTO();

                    return ResponseEntity.status(HttpStatus.OK).body(profesorOutputDTO);
                }
                case "full": {

                    Profesor profesor = servicioProfesor.buscarProfesor(id);

                    profesor.getPersona();

                    ProfesorPersonaOutputDTO profesorPersonaOutputDTO = profesor.aProfesorPersonaDTO();

                    return ResponseEntity.status(HttpStatus.OK).body(profesorPersonaOutputDTO);
                }

                default: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            }

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @GetMapping("")
    ResponseEntity devolverProfesores(){

        log.info("Buscando todos los profesores");
        List<Profesor> profesores = servicioProfesor.devolverProfesores();
        log.info("Cantidad de profesores: "+profesores.size());

        List<ProfesorOutputDTO> profesorOutputDTOS = profesores.stream()
                .map(Profesor::aProfesorDTO).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(profesorOutputDTOS);

    }

    @PostMapping("")
    ResponseEntity crearProfesor(@RequestBody ProfesorInputDTO profesorInputDTO){

        log.info("Creando profesor");
        try {

            Profesor profesor = servicioProfesor.crearProfesor(profesorInputDTO);

            ProfesorOutputDTO profesorOutputDTO = profesor.aProfesorDTO();

            return ResponseEntity.status(HttpStatus.CREATED).body(profesorOutputDTO);
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    ResponseEntity editaProfesor(@PathVariable String id, @RequestBody ProfesorInputDTO profesorInputDTO){

        log.info("Editando profesor con id: "+id);

        try {

            Profesor profesor = servicioProfesor.editarProfesor(profesorInputDTO, id);

            ProfesorOutputDTO profesorOutputDTO = profesor.aProfesorDTO();

            return ResponseEntity.status(HttpStatus.OK).body(profesorOutputDTO);

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    ResponseEntity eliminarProfesor(@PathVariable String id){

        log.info("Eliminando profesor con id: "+id);

        try {

            servicioProfesor.eliminarProfesor(id);

            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el profesor con ID: "+id);

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}
