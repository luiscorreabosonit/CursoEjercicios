package bosonit.practicas.ejercicios.content.persona.infrastructure.controller.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDTO {

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
