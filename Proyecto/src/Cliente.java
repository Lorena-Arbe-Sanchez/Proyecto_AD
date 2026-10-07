import java.io.Serializable;

// Con "implements Serializable" se permite que los objetos 'Cliente' puedan guardarse en un fichero binario
public class Cliente implements Serializable {

    // "id" para relacionar los clientes con los viajes (reservas)
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String dni;
    private String telefono;
    private String email;

    // Constructor con todos los atributos de la clase
    public Cliente(int id, String nombre, String apellido1, String apellido2, int edad, String dni,
                   String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Función para mostrar todos los datos de un cliente
    public void mostrarTodosDatos() {
        System.out.println("\nID: " + id +
                "\nNombre: " + nombre +
                "\nPrimer apellido: " + apellido1 +
                "\nSegundo apellido: " + apellido2 +
                "\nEdad: " + edad + " años" +
                "\nDNI: " + dni +
                "\nTeléfono: " + telefono +
                "\nEmail: " + email);
    }
}