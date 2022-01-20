package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Ciudad;
import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    @Autowired
    ServicioREST servicio;

    @GetMapping("/")
    String test (){

        return "Funciona controlador 1";

    }

    @GetMapping("/addPersona")
    ResponseEntity addPersona(@RequestHeader(value = "nombre", required = true) String nombre,
                              @RequestHeader(value = "poblacion", required = true) String poblacion,
                              @RequestHeader(value = "edad", required = true) Integer edad){

        Persona persona = servicio.crearPersona(nombre, poblacion, edad);

        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @PostMapping("/addCiudad")
    ResponseEntity addCiudad(@RequestBody Ciudad ciudad){

        servicio.addCiudad(ciudad);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
