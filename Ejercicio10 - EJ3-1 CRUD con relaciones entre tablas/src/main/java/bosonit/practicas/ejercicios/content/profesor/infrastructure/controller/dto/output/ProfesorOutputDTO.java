package bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.output;

import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorOutputDTO {

    private String id_profesor;

    private int persona;

    private String comentarios;

    private String rama;

    private List<String> alumnos;

}
