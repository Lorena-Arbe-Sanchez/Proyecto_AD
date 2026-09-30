import java.io.Serializable;
import java.util.Date;

public class Viaje implements Serializable {

    private int id;
    private int idDestino;
    // Poner el ID del hotel como "Integer" para tener la opción de guardarlo como "null" si el viaje es de solo 1 día sin alojamiento
    private Integer idHotel;
    private String origen;
    private Date fechaSalida;
    private Date fechaRegreso;
    private double precio;
    private int plazasTotales;
    private int plazasDisponibles;
    private String tipoViaje;

    public Viaje() {
    }

    public Viaje(int id, int idDestino, Integer idHotel, String origen, Date fechaSalida, Date fechaRegreso,
                 double precio, int plazasTotales, int plazasDisponibles, String tipoViaje) {
        this.id = id;
        this.idDestino = idDestino;
        this.idHotel = idHotel;
        this.origen = origen;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.precio = precio;
        this.plazasTotales = plazasTotales;
        this.plazasDisponibles = plazasDisponibles;
        this.tipoViaje = tipoViaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }

    public void setPlazasTotales(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public void mostrarTodosDatos() {
        System.out.println("\nID: " + id +
                "\nID del destino: " + idDestino +
                "\nID del hotel " + idHotel +
                "\nOrigen: " + origen +
                "\nFecha de salida: " + fechaSalida +
                "\nFecha de regreso: " + fechaRegreso +
                "\nPrecio: " + precio + "€" +
                "\nPlazas totales: " + plazasTotales +
                "\nPlazas disponibles: " + plazasDisponibles +
                "\nTipo de viaje: " + tipoViaje);
    }
}
