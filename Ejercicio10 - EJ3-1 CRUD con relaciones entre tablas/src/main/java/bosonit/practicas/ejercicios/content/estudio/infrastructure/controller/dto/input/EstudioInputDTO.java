package bosonit.practicas.ejercicios.content.estudio.infrastructure.controller.dto.input;

import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EstudioInputDTO {

    private String nombre;

    private String comentarios;

    private String profesor;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;


}
