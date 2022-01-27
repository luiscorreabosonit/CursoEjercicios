package bosonit.practicas.ejercicios.controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class Controlador {

    @Value("${VAR1}")
    String var1;

    @Value("${My.VAR2}")
    int var2;

    @Value("${VAR_SISTEMA:var3 no tiene valor}")
    String var3;

    @Value("${url}")
    String url;

    @Value("${password}")
    String password;

    @GetMapping("")
    ResponseEntity prueba(){

        log.info("Hay conexion");
        log.debug("Entra correctamente");
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @GetMapping("valores/")
    ResponseEntity devolverValores(){

        String valores = "Valor de var1 es: "+var1+", valor de my.var2 es: "+var2;
        log.info(valores);
        return ResponseEntity.status(HttpStatus.OK).body(valores);

    }

    @GetMapping("var3/")
    ResponseEntity devolverVar3(){

        String valores = "Valor de var3 es: "+var3;
        log.warn("Los valores pueden ser incorrectos - "+valores);
        log.error("No se ha procedido correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(valores);

    }

    @GetMapping("parametros")
    ResponseEntity devolverParametros(){

        String parametros = "La url es: "+url+", la contraseña es: "+password;
        log.error(parametros);
        return ResponseEntity.status(HttpStatus.OK).body(parametros);

    }

}
