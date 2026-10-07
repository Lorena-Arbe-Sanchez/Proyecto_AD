import java.io.Serializable;

public class Hotel implements Serializable {

    private int id;
    private String nombre;
    private int estrellas;
    private String direccion;
    private double precioNoche;
    private String servicios;

    public Hotel(int id, String nombre, int estrellas, String direccion, double precioNoche, String servicios) {
        this.id = id;
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.direccion = direccion;
        this.precioNoche = precioNoche;
        this.servicios = servicios;
    }

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

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public void mostrarTodosDatos() {
        System.out.println("\nID: " + id +
                "\nNombre: " + nombre +
                "\nEstrellas: " + estrellas +
                "\nDirección: " + direccion +
                "\nPrecio por noche: " + precioNoche + "€" +
                "\nServicios: " + servicios);
    }
}
