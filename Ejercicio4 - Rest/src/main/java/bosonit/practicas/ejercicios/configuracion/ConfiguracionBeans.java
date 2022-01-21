package bosonit.practicas.ejercicios.configuracion;

import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {

    @Bean
    @Qualifier("bean1")
    public Persona persona1() {
        Persona persona = new Persona();
        persona.setNombre("bean1");
        return persona;
    }

    @Bean
    @Qualifier("bean2")
    public Persona persona2() {
        Persona persona = new Persona();
        persona.setNombre("bean2");
        return persona;
    }

    @Bean
    @Qualifier("bean3")
    public Persona persona3() {
        Persona persona = new Persona();
        persona.setNombre("bean3");
        return persona;
    }
}
