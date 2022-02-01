package bosonit.practicas.ejercicios.content.estudiante.infrastructure.repository.jpa;


import bosonit.practicas.ejercicios.content.estudiante.domain.Estudiante;
import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudiantesRepository extends JpaRepository<Estudiante, String> {

    public Optional<Estudiante> findByPersona(Persona persona);

}
