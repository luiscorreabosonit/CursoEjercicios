package bosonit.practicas.ejercicios.content.estudio.domain;

import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToMany
    private List<Estudiante> estudiantes;

    private String nombre;

    private String comentarios;

    @NotNull
    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

}
