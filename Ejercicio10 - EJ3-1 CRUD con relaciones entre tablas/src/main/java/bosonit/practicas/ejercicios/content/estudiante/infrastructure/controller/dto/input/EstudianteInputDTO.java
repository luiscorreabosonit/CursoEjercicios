package bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input;

import bosonit.practicas.ejercicios.content.enums.Rama;
import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EstudianteInputDTO {

    private String id_estudiante;

    private int persona;

    private int num_hours_week;

    private String comentarios;

//    private String profesor;

    private String rama;

//    private List<String> estudios;


}
