package bosonit.practicas.ejercicios.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudad {

    private String nombre;
    private int numeroHabitantes;
}
