package bosonit.practicas.ejercicios.content.estudiante.domain;

import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_estudiante;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @NotNull
    @Column(name = "horas_por_semana")
    private int num_hours_week;

    @Column(name = "comentarios")
    private String comentarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @Column(name = "rama")
    private String rama;

    @ManyToMany
    private List<Estudio> estudios;


}
