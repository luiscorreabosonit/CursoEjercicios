package bosonit.practicas.ejercicios;

import bosonit.practicas.ejercicios.bosonit.practicas.ejercicios.modelos.Persona;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        List<Persona> personas = new ArrayList<>();

        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {

            archivo = new File ("ficheroEjercicio1.txt");
            fileReader = new FileReader (archivo);
            bufferedReader = new BufferedReader(fileReader);

            String linea;
            while((linea=bufferedReader.readLine())!=null) {

                Optional<String> nombre = Optional.empty();;
                Optional<String> poblacion = Optional.empty();;
                Optional<Integer> edad = Optional.empty();;

                String[] partes = linea.split(":");

                if(partes.length > 0 && partes [0] != "") nombre = Optional.ofNullable(partes[0]);
                if(partes.length > 1 && partes [1] != "") poblacion = Optional.ofNullable(partes[1]);
                if(partes.length > 2 && partes[2] != "") edad = Optional.of(Integer.parseInt(partes[2]));

                personas.add(new Persona(nombre.orElse("Desconocido"), poblacion.orElse("Desconocida"), edad.orElse(-1)));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{

            try{
                if( null != fileReader ){
                    fileReader.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        personas.stream().filter(persona -> persona.getEdad() < 25 && persona.getEdad() > 0)
                         .map(persona ->
                             "Nombre: " + persona.getNombre() +
                             ". Poblacion: " + persona.getPoblacion() +
                             ". Edad: " + persona.getEdad()
                         )
                            .forEach(System.out::println);

    }
}
