package bosonit.practicas.ejercicios.content.persona.infrastructure.controller;

import bosonit.practicas.ejercicios.content.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.persona.infrastructure.controller.dto.input.PersonaInputDTO;
import bosonit.practicas.ejercicios.content.persona.application.ServicioREST;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class Controlador {

    @Autowired
    ServicioREST servicioREST;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("")
    ResponseEntity prueba(){

        log.info("Hay conexion");
        log.debug("Entra correctamente");

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("personas/{id}")
    ResponseEntity buscarPersona(@PathVariable int id){

        log.info("Buscando persona con id: "+id);
//        try {

            Persona persona = servicioREST.buscarPorId(id);
            PersonaOutputDTO personaOutputDTO = modelMapper.map(persona, PersonaOutputDTO.class);

            log.info("Encontrada la persona con id: "+id);
            return ResponseEntity.status(HttpStatus.OK).body(personaOutputDTO);
//        }catch (RuntimeException e){
//            log.warn(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//
//        }

    }

    @GetMapping("personas/nombre/{nombre}")
    ResponseEntity buscarPersonaNombre(@PathVariable String nombre){

        log.info("Buscando personas con nombre: "+nombre);
        try {
            List<Persona> personas = servicioREST.buscarPorNombre(nombre);
            log.info("Encontradas personas con nombre: "+nombre);
            return ResponseEntity.status(HttpStatus.OK).body(personas);
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

    @GetMapping("personas")
    ResponseEntity devolverPersonas(){

        log.info("Buscando todas las personas");
        List<Persona> personas = servicioREST.devolverPersonas();
        log.info("Cantidad de personas: "+personas.size());

        List<PersonaOutputDTO> personasDTO = personas.stream()
                .map(persona -> modelMapper.map(persona, PersonaOutputDTO.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(personasDTO);

    }

    @PostMapping("personas")
    ResponseEntity crearPersona(@RequestBody PersonaInputDTO personaDTO){

        log.info("Creando persona");
//        try {

            Persona persona = servicioREST.crearPersona(personaDTO);

            PersonaOutputDTO personaOutputDTO = modelMapper.map(persona, PersonaOutputDTO.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(personaOutputDTO);
//        }catch (RuntimeException e){
//            log.warn(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//
//        }
    }

}
