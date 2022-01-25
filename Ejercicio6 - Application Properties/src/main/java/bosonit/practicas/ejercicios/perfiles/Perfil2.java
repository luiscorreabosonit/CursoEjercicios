package bosonit.practicas.ejercicios.perfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Profile("perfil2")
public class Perfil2 implements Perfiles{

    String valor = "Estas en el perfil 2";

    public Perfil2() {
    }

    @GetMapping("/perfil")
    @Override
    public void miFuncion() {
        System.out.println(valor);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}