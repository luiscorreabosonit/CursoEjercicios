package bosonit.practicas.ejercicios.CRUD;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class DeleteController {

    @Autowired
    ServicioREST servicioREST;

    @DeleteMapping("/{id}")
    ResponseEntity borrarPersonaId(@PathVariable String id){

        int nuevoId = Integer.parseInt(id);

        Boolean borrado = servicioREST.borrarPersonaId(nuevoId);

        if (!borrado)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
