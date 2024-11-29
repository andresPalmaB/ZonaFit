package zona_fit.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Client {

    private int idClient;
    private String name;
    private String lastName;
    private int membership;

    public Client(int idcliente) {
        this.idClient = idcliente;
    }

    public Client(String nombre, String apellido, int membresia) {
        this.name = nombre;
        this.lastName = apellido;
        this.membership = membresia;
    }

}
