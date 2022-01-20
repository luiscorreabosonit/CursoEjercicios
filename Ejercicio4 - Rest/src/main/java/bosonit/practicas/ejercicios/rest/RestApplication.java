package bosonit.practicas.ejercicios.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"bosonit.practicas.ejercicios.controladores",
		"bosonit.practicas.ejercicios.CRUD",
		"bosonit.practicas.ejercicios.servicios"

})
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
