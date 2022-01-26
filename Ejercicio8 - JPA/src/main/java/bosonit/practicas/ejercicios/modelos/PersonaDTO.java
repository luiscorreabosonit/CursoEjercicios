package bosonit.practicas.ejercicios.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    String usuario;
    String password;
    String name;
    String surname;
    String companyName;
    String personalEmail;
    String city;
    Boolean active;
    LocalDateTime createdDate;
    String imagenUrl;
    LocalDateTime terminationDate;
}
