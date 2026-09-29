import java.io.Serializable;

// Con "implements Serializable" se permite que los objetos 'Cliente' puedan guardarse en un fichero binario
public class Cliente implements Serializable {

    // "id" para relacionar los clientes con los viajes (reserva)
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String dni;
    private String telefono;
    private String email;

    /*public Cliente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Cliente() {
        this.nombre = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrar() {
        System.out.println("\nDatos del coche -->");
        System.out.println("Marca: " + marca);
        System.out.println("Potencia: " + potencia);
        System.out.println("Precio: " + precio);
    }*/
}