package bosonit.practicas.ejercicios.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "bosonit.practicas.ejercicios.content.persona.infrastructure.controller",
        "bosonit.practicas.ejercicios.content.persona.application",
        "bosonit.practicas.ejercicios.content.persona.infrastructure.repository.jpa"
})

@EnableJpaRepositories(basePackages = {"bosonit.practicas.ejercicios.content.persona.infrastructure.repository.jpa"})

@EntityScan("bosonit.practicas.ejercicios.content.persona.domain")
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

}
