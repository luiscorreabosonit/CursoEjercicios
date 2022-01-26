package bosonit.practicas.ejercicios.repositorios;


import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonasRepository extends JpaRepository<Persona, Integer> {

    public Optional<List<Persona>> buscarPorNombre(String nombre);

}
