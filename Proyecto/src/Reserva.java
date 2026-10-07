import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {

    private int id;
    private int idCliente;
    private int idViaje;
    private Date fechaReserva;
    private int numeroPersonas;
    private double precioTotal;
    private String estado;

    public Reserva(int id, int idCliente, int idViaje, Date fechaReserva, int numeroPersonas,
                   double precioTotal, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idViaje = idViaje;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarTodosDatos() {
        System.out.println("\nID: " + id +
                "\nID del cliente: " + idCliente +
                "\nID del viaje: " + idViaje +
                "\nFecha de reserva: " + fechaReserva +
                "\nNúmero de personas: " + numeroPersonas +
                "\nPrecio total: " + precioTotal +
                "\nEstado: " + estado);
    }
}
