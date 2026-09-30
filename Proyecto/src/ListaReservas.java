import java.util.ArrayList;
import java.util.List;

public class ListaReservas {

    private List<Reserva> lista;

    public ListaReservas() {
        lista = new ArrayList<>();
    }

    public void anadir(Reserva reserva) {
        lista.add(reserva);
    }

    public List<Reserva> getLista() {
        return lista;
    }

    public void setLista(List<Reserva> lista) {
        this.lista = lista;
    }
}
