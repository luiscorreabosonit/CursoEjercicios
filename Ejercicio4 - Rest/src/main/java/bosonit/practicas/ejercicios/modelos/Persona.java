package bosonit.practicas.ejercicios.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private String nombre;
    private String poblacion;
    private int edad;

    @Bean
    @Qualifier("bean1")
    public void bean1(){
        setNombre("bean1");
    }

    @Bean
    @Qualifier("bean2")
    public void bean2(){
        setNombre("bean2");
    }

    @Bean
    @Qualifier("bean3")
    public void bean3(){
        setNombre("bean3");
    }
}
