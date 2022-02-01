package bosonit.practicas.ejercicios.content.profesor.infrastructure.repository.jpa;


import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import bosonit.practicas.ejercicios.content.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesoresRepository extends JpaRepository<Profesor, String> {

}
