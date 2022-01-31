package bosonit.practicas.ejercicios.content.persona.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    int id;

    String usuario;
    String password;
    String name;
    String surname;
    String companyName;
    String personalEmail;
    String city;
    Boolean active;
    LocalDateTime createdDate;
    String imagenUrl;
    LocalDateTime terminationDate;

    public Persona(){

        this.createdDate = LocalDateTime.now();

    }

    public Persona(String _usuario, String _password, String _name, String _surname, String _companyName, String _personalEmail,
                   String _city, Boolean _active, String _imagenUrl){

        this.usuario=_usuario;
        this.password=_password;
        this.name=_name;
        this.surname=_surname;
        this.companyName=_companyName;
        this.personalEmail=_personalEmail;
        this.city=_city;
        this.active=_active;
        this.imagenUrl=_imagenUrl;
        this.createdDate = LocalDateTime.now();
        this.terminationDate=null;

    }


}
