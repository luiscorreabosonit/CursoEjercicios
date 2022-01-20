package bosonit.practicas.ejercicios.servicios;

import bosonit.practicas.ejercicios.modelos.Ciudad;
import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioREST {

    private Persona persona = new Persona();
    private List<Ciudad> ciudades = new ArrayList<>();

    public Persona crearPersona(String nombre, String poblacion, int edad){

        Persona nuevaPersona = new Persona(nombre, poblacion, edad);

        this.persona = nuevaPersona;

        return this.persona;

    }

    public void addCiudad(Ciudad nuevaCiudad){
        ciudades.add(nuevaCiudad);
    }

    public Persona obtenerPersona(){
        return this.persona;
    }

    public List<Ciudad> obtenerCiudades(){
        return this.ciudades;
    }

}
