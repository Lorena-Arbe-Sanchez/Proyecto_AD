import java.util.ArrayList;
import java.util.List;

public class ListaViajes {

    private List<Viaje> lista;

    public ListaViajes() {
        lista = new ArrayList<>();
    }

    public void anadir(Viaje viaje) {
        lista.add(viaje);
    }

    public List<Viaje> getLista() {
        return lista;
    }

    public void setLista(List<Viaje> lista) {
        this.lista = lista;
    }
}
