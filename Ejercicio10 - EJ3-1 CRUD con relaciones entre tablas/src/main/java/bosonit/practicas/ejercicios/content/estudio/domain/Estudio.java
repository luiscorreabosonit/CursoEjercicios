package bosonit.practicas.ejercicios.content.estudio.domain;

import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudios_seq")
    @GenericGenerator(
            name = "estudios_seq",
            strategy = "bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MAT"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id;

    @ManyToMany
    private List<Estudiante> estudiantes;

    private String nombre;

    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profesor profesor;

    @NotNull
    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

}
