package bosonit.practicas.ejercicios.servicios;

import bosonit.practicas.ejercicios.modelos.Ciudad;
import bosonit.practicas.ejercicios.modelos.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioREST {

    private Persona persona = new Persona();
    private List<Ciudad> ciudades = new ArrayList<>();
    private List<Persona> personas = new ArrayList<>();

    public Persona crearPersona(String nombre, String poblacion, int edad){

        Persona nuevaPersona = new Persona(nombre, poblacion, edad);

        this.persona = nuevaPersona;

        return this.persona;

    }

    public void addCiudad(Ciudad nuevaCiudad){
        ciudades.add(nuevaCiudad);
    }

    public void addPersona(Persona nuevaPersona){
        personas.add(nuevaPersona);
    }

    public Persona busquedaPersonaId(int id){
        if(personas.size()>id) {
            return personas.get(id);
        }else {
            return null;
        }
    }

    public Boolean borrarPersonaId(int id){
        if(personas.size()>id) {
            personas.remove(id);
            return true;
        }else {
            return false;
        }
    }

    public List<Persona> busquedaPersonaNombre(String nombre){
        return personas.stream().filter(persona -> persona.getNombre().equals(nombre)).collect(Collectors.toList());
    }


    public Persona obtenerPersona(){
        return this.persona;
    }

    public List<Ciudad> obtenerCiudades(){
        return this.ciudades;
    }

}
