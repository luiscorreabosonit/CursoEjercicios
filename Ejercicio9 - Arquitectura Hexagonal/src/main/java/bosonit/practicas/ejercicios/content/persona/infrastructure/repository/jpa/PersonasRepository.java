package bosonit.practicas.ejercicios.content.persona.infrastructure.repository.jpa;


import bosonit.practicas.ejercicios.content.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonasRepository extends JpaRepository<Persona, Integer> {

    public Optional<List<Persona>> findByName(String name);

}
