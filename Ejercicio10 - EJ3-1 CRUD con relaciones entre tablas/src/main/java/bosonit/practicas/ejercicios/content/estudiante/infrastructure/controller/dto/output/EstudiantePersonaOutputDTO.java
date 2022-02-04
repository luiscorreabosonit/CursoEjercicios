package bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EstudiantePersonaOutputDTO {

    private String id_estudiante;
    private int num_hours_week;
    private String comentarios;
    private String rama;
    private String profesor;
    private List<String> estudios;

    private int idPersona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyName;
    private String personalEmail;
    private String city;
    private Boolean active;
    private LocalDateTime createdDate;
    private String imagenUrl;
    private LocalDateTime terminationDate;

}
