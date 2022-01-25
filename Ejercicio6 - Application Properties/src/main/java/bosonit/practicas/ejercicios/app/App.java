package bosonit.practicas.ejercicios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "bosonit.practicas.ejercicios.controladores",
        "bosonit.practicas.ejercicios.configuracion",
        "bosonit.practicas.ejercicios.perfiles"
})
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

}
