package bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller;

import bosonit.practicas.ejercicios.content.estudiante.application.ServicioEstudiante;
import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import bosonit.practicas.ejercicios.content.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
@Slf4j
public class ControladorEstudiante {

    @Autowired
    ServicioEstudiante servicioEstudiante;

    @GetMapping("/{id}")
    ResponseEntity buscarEstudiante(@PathVariable String id, @RequestParam(defaultValue = "simple") String outputType){

        log.info("Buscando estudiante con id: "+id);

        try {

        switch (outputType.toLowerCase()) {

            case "simple": {

                Estudiante estudiante = servicioEstudiante.buscarEstudiante(id);

                EstudianteOutputDTO estudianteOutputDTO = estudiante.aEstudianteDTO();

                return ResponseEntity.status(HttpStatus.OK).body(estudianteOutputDTO);
            }
            case "full": {

                Estudiante estudiante = servicioEstudiante.buscarEstudiante(id);

                estudiante.getPersona();

                EstudiantePersonaOutputDTO estudiantePersonaOutputDTO = estudiante.aEstudiantePersonaDTO();

                return ResponseEntity.status(HttpStatus.OK).body(estudiantePersonaOutputDTO);
            }

            default: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

//    @GetMapping("personas/nombre/{nombre}")
//    ResponseEntity buscarPersonaNombre(@PathVariable String nombre){
//
//        log.info("Buscando personas con nombre: "+nombre);
//        try {
//            List<Persona> personas = servicioREST.buscarPorNombre(nombre);
//            log.info("Encontradas personas con nombre: "+nombre);
//            return ResponseEntity.status(HttpStatus.OK).body(personas);
//        }catch (RuntimeException e){
//            log.warn(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//
//        }
//
//    }

    @GetMapping("")
    ResponseEntity devolverEstudiantes(){

        log.info("Buscando todos los estudiantes");
        List<Estudiante> estudiantes = servicioEstudiante.devolverEstudiantes();
        log.info("Cantidad de estudiantes: "+estudiantes.size());

        List<EstudianteOutputDTO> estudianteOutputDTOS = estudiantes.stream()
                .map(Estudiante::aEstudianteDTO).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(estudianteOutputDTOS);

    }

    @PostMapping("")
    ResponseEntity crearEstudiante(@RequestBody EstudianteInputDTO estudianteInputDTO){

        log.info("Creando estudiante");
        try {

            Estudiante estudiante = servicioEstudiante.crearEstudiante(estudianteInputDTO);

            EstudianteOutputDTO estudianteOutputDTO = estudiante.aEstudianteDTO();

            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteOutputDTO );
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    ResponseEntity editarEstudiante(@PathVariable String id, @RequestBody EstudianteInputDTO estudianteInputDTO){

        log.info("Editando estudiante con id: "+id);

        try {

            Estudiante estudiante = servicioEstudiante.editarEstudiante(estudianteInputDTO, id);

            EstudianteOutputDTO estudianteOutputDTO = estudiante.aEstudianteDTO();

            return ResponseEntity.status(HttpStatus.OK).body(estudianteOutputDTO);

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    ResponseEntity eliminarEstudiante(@PathVariable String id){

        log.info("Eliminando estudiante con id: "+id);

        try {

            servicioEstudiante.eliminarEstudiante(id);

            return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el estudiante con ID: "+id);

        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}
