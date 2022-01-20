package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    @GetMapping("/bean/{bean}")
    ResponseEntity obtenerBean(@PathVariable String bean){

        Persona persona = new Persona();

        switch(bean){

            case "bean1":
                persona.bean1();
                return ResponseEntity.status(HttpStatus.OK).body(persona);
            case "bean2":
                persona.bean2();
                return ResponseEntity.status(HttpStatus.OK).body(persona);
            case "bean3":
                persona.bean3();
                return ResponseEntity.status(HttpStatus.OK).body(persona);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
