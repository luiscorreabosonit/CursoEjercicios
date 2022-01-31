package bosonit.practicas.ejercicios.content.estudio.infrastructure.repository.jpa;


import bosonit.practicas.ejercicios.content.estudio.domain.Estudio;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudiosRepository extends JpaRepository<Estudio, Integer> {

}
