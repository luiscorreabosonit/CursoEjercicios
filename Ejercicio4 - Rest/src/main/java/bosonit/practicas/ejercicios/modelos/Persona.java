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
    
}
