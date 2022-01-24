package bosonit.practicas.ejercicios.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {

	/* Se guardan los argumentos en la variable estatica local */
	static String[] argumentos;

	/* Es el primero en ejecutarse ya que al llevar la etiqueta se realiza después del constructor */
	@PostConstruct
	void primeraFuncion(){

		System.out.println("Hola desde la clase inicial");

	}

	/* Es el segundo en ejecutarse ya que es el primer bean que se encuentra en la clase */
	@Bean
	CommandLineRunner ejecutame2(){
	return p -> {
		System.out.println("Hola desde la clase secundaria");
	};
	}

	/* Es el tercero en ejecutarse ya que es el siguiente bean que se encuentra en la clase */
	@Bean
	CommandLineRunner ejecutame3(){
		return p -> {
			System.out.println("Soy la tercera clase");
			System.out.println(Arrays.stream(argumentos).collect(Collectors.toList()));
		};
	}

	public static void main(String[] args) {

		argumentos = args;
		SpringApplication.run(App.class, args);


	}

}
