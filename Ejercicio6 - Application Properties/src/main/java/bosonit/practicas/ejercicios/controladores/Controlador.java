package bosonit.practicas.ejercicios.controladores;

import bosonit.practicas.ejercicios.configuracion.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

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

    @Autowired
    AppConfig configuracion;

    @GetMapping("")
    ResponseEntity prueba(){

        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @GetMapping("valores/")
    ResponseEntity devolverValores(){

        String valores = "Valor de var1 es: "+var1+", valor de my.var2 es: "+var2;

        return ResponseEntity.status(HttpStatus.OK).body(valores);

    }

    @GetMapping("var3/")
    ResponseEntity devolverVar3(){

        String valores = "Valor de var3 es: "+var3;

        return ResponseEntity.status(HttpStatus.OK).body(valores);

    }

    @GetMapping("parametros")
    ResponseEntity devolverParametros(){

        String parametros = "La url es: "+url+", la contraseña es: "+password;

        return ResponseEntity.status(HttpStatus.OK).body(parametros);

    }

    @GetMapping("miconfiguracion")
    ResponseEntity devolverConfiguracion(){

        String configuracionFinal = "El valor 1 es: "+ this.configuracion.getValor1() +", el valor 2 es: "+this.configuracion.getValor2();

        return ResponseEntity.status(HttpStatus.OK).body(configuracionFinal);

    }

}
