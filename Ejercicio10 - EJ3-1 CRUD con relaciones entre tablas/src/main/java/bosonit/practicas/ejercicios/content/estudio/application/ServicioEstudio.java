package bosonit.practicas.ejercicios.content.estudio.application;

import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.estudio.infrastructure.controller.dto.input.EstudioInputDTO;
import bosonit.practicas.ejercicios.content.estudio.infrastructure.repository.jpa.EstudiosRepository;
import bosonit.practicas.ejercicios.content.persona.application.ServicioPersona;
import bosonit.practicas.ejercicios.content.profesor.application.ServicioProfesor;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEstudio {

    @Autowired
    EstudiosRepository repository;

    @Autowired
    ServicioPersona servicioPersona;

    @Autowired
    ServicioProfesor servicioProfesor;

    public Estudio buscarEstudio(String id){

        return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún estudio con ese Id"));

    }

    public Estudio crearEstudio(EstudioInputDTO estudioInputDTO) {

        Profesor profesor = servicioProfesor.buscarProfesor(estudioInputDTO.getProfesor());

        Estudio estudio = new Estudio(estudioInputDTO, profesor);

        repository.save(estudio);

        Estudio estudioGuardado = repository.getById(estudio.getId_estudio());

        return estudioGuardado;

    }

    public Estudio editarEstudio(EstudioInputDTO estudioInputDTO, String id){

        Estudio estudio = repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ningún estudio con ese Id"));

        if(estudioInputDTO.getFechaInicio()!=null) estudio.setFechaInicio(estudioInputDTO.getFechaInicio());
        if(estudioInputDTO.getFechaFin()!=null) estudio.setFechaFin(estudioInputDTO.getFechaFin());
        if(estudioInputDTO.getComentarios()!=null) estudio.setComentarios(estudioInputDTO.getComentarios());
        if(estudioInputDTO.getNombre()!=null) estudio.setNombre(estudioInputDTO.getNombre());
        if(estudioInputDTO.getProfesor()!=null){

        Profesor profesor = servicioProfesor.buscarProfesor(estudioInputDTO.getProfesor());
        estudio.setProfesor(profesor);

        }

        repository.save(estudio);

        return estudio;

    }

    public void eliminarEstudio(String idEstudio){

        repository.findById(idEstudio).orElseThrow(() -> new RuntimeException("El estudio con ID "+idEstudio+" no se ha encontrado"));

        repository.deleteById(idEstudio);

    }

    public List<Estudio> devolverEstudios(){

        return repository.findAll();

    }

}
