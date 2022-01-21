package bosonit.practicas.ejercicios.CRUD;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class UpdateController {

    @Autowired
    ServicioREST servicioREST;

    @PutMapping("/{id}")
    ResponseEntity actualizarPersona(@PathVariable int id, @RequestBody Persona persona){

        Boolean cambiado = servicioREST.actualizarPersona(id, persona);

        if (cambiado == false)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(persona);

    }
}
