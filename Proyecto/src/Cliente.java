import java.io.Serializable;

// Con "implements Serializable" se permite que los objetos 'Cliente' puedan guardarse en un fichero binario
public class Cliente implements Serializable {

    // TODO : ¿id?

    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String dni;
    private String telefono;

    /*public Cliente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Cliente() {
        this.nombre = null;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void mostrar() {
        System.out.println("\nDatos del coche -->");
        System.out.println("Marca: " + marca);
        System.out.println("Potencia: " + potencia);
        System.out.println("Precio: " + precio);
    }*/
}