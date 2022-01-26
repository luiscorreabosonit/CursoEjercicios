package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.modelos.PersonaDTO;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class Controlador {

    @Autowired
    ServicioREST servicioREST;

    @GetMapping("")
    ResponseEntity prueba(){

        log.info("Hay conexion");
        log.debug("Entra correctamente");
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("personas/{id}")
    ResponseEntity buscarPersona(@PathVariable int id){

        log.info("Buscando persona con id: "+id);
        try {
            Persona persona = servicioREST.buscarPorId(id);
            log.info("Encontrada la persona con id: "+id);
            return ResponseEntity.status(HttpStatus.OK).body(persona);
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

    @GetMapping("personas/")
    ResponseEntity buscarPersonaNombre(@RequestParam String nombre){

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
        return ResponseEntity.status(HttpStatus.OK).body(personas);

    }

    @PostMapping("personas")
    ResponseEntity crearPersona(@RequestBody PersonaDTO personaDTO){

        log.info("Creando persona");
        try {
            servicioREST.crearPersona(personaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

}
