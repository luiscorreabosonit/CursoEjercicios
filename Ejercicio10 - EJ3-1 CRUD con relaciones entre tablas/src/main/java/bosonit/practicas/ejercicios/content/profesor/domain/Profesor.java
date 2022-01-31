package bosonit.practicas.ejercicios.content.profesor.domain;

import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(name = "comentarios")
    private String comentarios;

    @NotNull
    @Column(name = "rama")
    private String rama;

}
