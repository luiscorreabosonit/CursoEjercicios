package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    List<Persona> personas = new ArrayList<>();

    @GetMapping("")
    String test (){

        return "Funciona normal";

    }

    @GetMapping("/user/{nombre}")
    ResponseEntity buscarPersona(@PathVariable("nombre") String nombre){

        List<Persona> busqueda = personas.stream().filter(persona -> persona.getNombre().toLowerCase().equals(nombre.toLowerCase())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);
    }

    @PostMapping("/useradd")
    ResponseEntity addPersona(@RequestBody Persona persona){

        persona.setEdad(persona.getEdad()+1);
        personas.add(persona);

        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }
}
