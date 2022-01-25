package bosonit.practicas.ejercicios.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:miconfiguracion.properties")
public class AppConfig {

    @Value("${valor1}")
    String valor1;

    @Value("${valor2}")
    String valor2;

    @PostConstruct
    void imprimirValores(){

        System.out.println("Valor 1 es: "+valor1+", valor 2 es: "+valor2);

    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }
}
