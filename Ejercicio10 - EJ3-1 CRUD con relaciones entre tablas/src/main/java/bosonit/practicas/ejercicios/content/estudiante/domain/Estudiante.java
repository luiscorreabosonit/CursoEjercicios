package bosonit.practicas.ejercicios.content.estudiante.domain;

import bosonit.practicas.ejercicios.content.enums.Rama;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiantes_seq")
    @GenericGenerator(
            name = "estudiantes_seq",
            strategy = "bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_estudiante;

    @OneToOne
    private Persona persona;

    @NotNull
    @Column(name = "horas_por_semana")
    private int num_hours_week;

    @Column(name = "comentarios")
    private String comentarios;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_profesor")
//    private Profesor profesor;

    @Column(name = "rama")
    @Enumerated(value = EnumType.STRING)
    private Rama rama;

//    @ManyToMany
//    private List<Estudio> estudios;

    public Estudiante(EstudianteInputDTO estudianteInputDTO, Persona persona){

        this.comentarios = estudianteInputDTO.getComentarios();

        switch (estudianteInputDTO.getRama()) {

            case "BACK": this.rama = Rama.BACK;
                break;

            case "FRONT": this.rama = Rama.FRONT;
                break;

            case "FULLSTACK": this.rama = Rama.FULLSTACK;
                break;

            default: this.rama = null;

        }

        this.num_hours_week = estudianteInputDTO.getNum_hours_week();

        this.persona = persona;

    }

    public EstudianteOutputDTO aEstudianteDTO(){

        EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO();

        if(this.id_estudiante != null) estudianteOutputDTO.setId_estudiante(this.id_estudiante);
        if(this.comentarios != null) estudianteOutputDTO.setComentarios(this.comentarios);
        if(this.persona != null) estudianteOutputDTO.setPersona(this.persona.getId());
        if(this.rama != null) estudianteOutputDTO.setRama(this.rama.name());
        if(this.num_hours_week != 0) estudianteOutputDTO.setNum_hours_week(this.num_hours_week);

        return estudianteOutputDTO;

    }

    public EstudiantePersonaOutputDTO aEstudiantePersonaDTO(){

        EstudiantePersonaOutputDTO estudiantePersonaOutputDTO = new EstudiantePersonaOutputDTO();

        if(this.id_estudiante != null) estudiantePersonaOutputDTO.setId_estudiante(this.id_estudiante);
        if(this.comentarios != null) estudiantePersonaOutputDTO.setComentarios(this.comentarios);
        if(this.rama != null) estudiantePersonaOutputDTO.setRama(this.rama.name());
        if(this.num_hours_week != 0) estudiantePersonaOutputDTO.setNum_hours_week(this.num_hours_week);


        if(this.persona.getId() != 0) estudiantePersonaOutputDTO.setIdPersona(this.persona.getId());
        if(this.persona.getActive() != null) estudiantePersonaOutputDTO.setActive(this.persona.getActive());
        if(this.persona.getPersonalEmail() != null) estudiantePersonaOutputDTO.setPersonalEmail(this.persona.getPersonalEmail());
        if(this.persona.getCity() != null) estudiantePersonaOutputDTO.setCity(this.persona.getCity());
        if(this.persona.getCompanyName() != null) estudiantePersonaOutputDTO.setCompanyName(this.persona.getCompanyName());
        if(this.persona.getCreatedDate() != null) estudiantePersonaOutputDTO.setCreatedDate(this.persona.getCreatedDate());
        if(this.persona.getUsuario() != null) estudiantePersonaOutputDTO.setUsuario(this.persona.getUsuario());
        if(this.persona.getPassword() != null) estudiantePersonaOutputDTO.setPassword(this.persona.getPassword());
        if(this.persona.getName() != null) estudiantePersonaOutputDTO.setName(this.persona.getName());
        if(this.persona.getSurname() != null) estudiantePersonaOutputDTO.setSurname(this.persona.getSurname());
        if(this.persona.getImagenUrl() != null) estudiantePersonaOutputDTO.setImagenUrl(this.persona.getImagenUrl());
        if(this.persona.getTerminationDate() != null) estudiantePersonaOutputDTO.setTerminationDate(this.persona.getTerminationDate());

        return estudiantePersonaOutputDTO;

    }

}
