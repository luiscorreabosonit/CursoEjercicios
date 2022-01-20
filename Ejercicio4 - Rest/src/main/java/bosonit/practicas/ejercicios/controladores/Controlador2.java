package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Ciudad;
import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    @Autowired
    ServicioREST servicio;

    @GetMapping("/getPersona")
    ResponseEntity obtenerPersona(){

        Persona persona = servicio.obtenerPersona();

        persona.setEdad(persona.getEdad()*2);
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @GetMapping("/getCiudad")
    ResponseEntity obtenerCiudades(){

        List<Ciudad> ciudades = servicio.obtenerCiudades();

        return ResponseEntity.status(HttpStatus.OK).body(ciudades);
    }

}
