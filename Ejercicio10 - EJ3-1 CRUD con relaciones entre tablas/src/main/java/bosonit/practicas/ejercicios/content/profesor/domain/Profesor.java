package bosonit.practicas.ejercicios.content.profesor.domain;

import bosonit.practicas.ejercicios.content.enums.Rama;
import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.input.EstudianteInputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudianteOutputDTO;
import bosonit.practicas.ejercicios.content.estudiante.infrastructure.controller.dto.output.EstudiantePersonaOutputDTO;
import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.input.ProfesorInputDTO;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import bosonit.practicas.ejercicios.content.profesor.infrastructure.controller.dto.output.ProfesorPersonaOutputDTO;
import bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesores_seq")
    @GenericGenerator(
            name = "profesores_seq",
            strategy = "bosonit.practicas.ejercicios.content.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_profesor;

    @OneToOne(optional = false)
    private Persona persona;

    @Column(name = "comentarios")
    private String comentarios;

    @NotNull
    @Column(name = "rama")
    @Enumerated(value = EnumType.STRING)
    private Rama rama;

    @OneToMany
    private List<Estudiante> alumnos;

//    @OneToMany
//    private List<Estudio> estudios;

    public Profesor(ProfesorInputDTO profesorInputDTO, Persona persona){

        this.comentarios = profesorInputDTO.getComentarios();

        switch (profesorInputDTO.getRama()) {

            case "BACK": this.rama = Rama.BACK;
                break;

            case "FRONT": this.rama = Rama.FRONT;
                break;

            case "FULLSTACK": this.rama = Rama.FULLSTACK;
                break;

            default: this.rama = null;

        }

        this.persona = persona;

    }

    public ProfesorOutputDTO aProfesorDTO(){

        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO();

        if(this.id_profesor != null) profesorOutputDTO.setId_profesor(this.id_profesor);
        if(this.comentarios != null) profesorOutputDTO.setComentarios(this.comentarios);
        if(this.persona != null) profesorOutputDTO.setPersona(this.persona.getId());
        if(this.rama != null) profesorOutputDTO.setRama(this.rama.name());
        if(this.getAlumnos() != null) profesorOutputDTO.setAlumnos(this.alumnos.stream().map(Estudiante::getId_estudiante).collect(Collectors.toList()));

        return profesorOutputDTO;

    }

    public ProfesorPersonaOutputDTO aProfesorPersonaDTO(){

        ProfesorPersonaOutputDTO profesorPersonaOutputDTO = new ProfesorPersonaOutputDTO();

        if(this.id_profesor != null) profesorPersonaOutputDTO.setId_profesor(this.id_profesor);
        if(this.comentarios != null) profesorPersonaOutputDTO.setComentarios(this.comentarios);
        if(this.persona != null) profesorPersonaOutputDTO.setPersona(this.persona.getId());
        if(this.rama != null) profesorPersonaOutputDTO.setRama(this.rama.name());
        if(this.getAlumnos() != null) profesorPersonaOutputDTO.setAlumnos(this.alumnos.stream().map(Estudiante::getId_estudiante).collect(Collectors.toList()));

        if(this.persona.getId() != 0) profesorPersonaOutputDTO.setIdPersona(this.persona.getId());
        if(this.persona.getActive() != null) profesorPersonaOutputDTO.setActive(this.persona.getActive());
        if(this.persona.getPersonalEmail() != null) profesorPersonaOutputDTO.setPersonalEmail(this.persona.getPersonalEmail());
        if(this.persona.getCity() != null) profesorPersonaOutputDTO.setCity(this.persona.getCity());
        if(this.persona.getCompanyName() != null) profesorPersonaOutputDTO.setCompanyName(this.persona.getCompanyName());
        if(this.persona.getCreatedDate() != null) profesorPersonaOutputDTO.setCreatedDate(this.persona.getCreatedDate());
        if(this.persona.getUsuario() != null) profesorPersonaOutputDTO.setUsuario(this.persona.getUsuario());
        if(this.persona.getPassword() != null) profesorPersonaOutputDTO.setPassword(this.persona.getPassword());
        if(this.persona.getName() != null) profesorPersonaOutputDTO.setName(this.persona.getName());
        if(this.persona.getSurname() != null) profesorPersonaOutputDTO.setSurname(this.persona.getSurname());
        if(this.persona.getImagenUrl() != null) profesorPersonaOutputDTO.setImagenUrl(this.persona.getImagenUrl());
        if(this.persona.getTerminationDate() != null) profesorPersonaOutputDTO.setTerminationDate(this.persona.getTerminationDate());

        return profesorPersonaOutputDTO;

    }

}
