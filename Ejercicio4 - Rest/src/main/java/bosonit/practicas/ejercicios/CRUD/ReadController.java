package bosonit.practicas.ejercicios.CRUD;

import bosonit.practicas.ejercicios.modelos.Persona;
import bosonit.practicas.ejercicios.servicios.ServicioREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ReadController {

    @Autowired
    ServicioREST servicioREST;

    @GetMapping("/{id}")
    ResponseEntity buscarPersonaId(@PathVariable String id){

        int nuevoId = Integer.parseInt(id);

        Persona busqueda = servicioREST.busquedaPersonaId(nuevoId);

        if (busqueda == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);

    }

    @GetMapping("/nombre/{nombre}")
    ResponseEntity buscarPersonaNombre(@PathVariable String nombre){

        List<Persona> busqueda = servicioREST.busquedaPersonaNombre(nombre);

        return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);

    }

}
