import java.util.ArrayList;
import java.util.List;

public class ListaViajes {

    private final List<Viaje> lista;

    public ListaViajes() {
        lista = new ArrayList<>();
    }

    public void anadir(Viaje viaje) {
        lista.add(viaje);
    }
}
