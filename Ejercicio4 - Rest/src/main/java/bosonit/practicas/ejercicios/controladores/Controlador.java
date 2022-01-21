package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    @Autowired
    @Qualifier("bean1")
    Persona persona1;

    @Autowired
    @Qualifier("bean2")
    Persona persona2;

    @Autowired
    @Qualifier("bean3")
    Persona persona3;

    @GetMapping("/bean/{bean}")
    ResponseEntity obtenerBean(@PathVariable String bean){

        switch(bean){

            case "bean1":
                return ResponseEntity.status(HttpStatus.OK).body(persona1);
            case "bean2":
                return ResponseEntity.status(HttpStatus.OK).body(persona2);
            case "bean3":
                return ResponseEntity.status(HttpStatus.OK).body(persona3);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
