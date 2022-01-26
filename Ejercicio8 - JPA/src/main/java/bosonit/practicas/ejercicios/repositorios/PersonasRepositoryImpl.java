package bosonit.practicas.ejercicios.repositorios;


import bosonit.practicas.ejercicios.modelos.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class PersonasRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<List<Persona>> buscarPorNombre(String nombre){

        String query = "SELECT p FROM Persona p WHERE p.name = '"+nombre+"'";
        Optional<List<Persona>> personas = Optional.ofNullable(entityManager.createQuery(query).getResultList());

        return personas;
    }

}
