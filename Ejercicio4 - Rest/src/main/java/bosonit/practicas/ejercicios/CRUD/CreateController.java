package bosonit.practicas.ejercicios.CRUD;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class CreateController {

    @Autowired
    ServicioREST servicioREST;

    @PostMapping("")
    ResponseEntity crearPersona(@RequestBody Persona persona){

        servicioREST.addPersona(persona);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
