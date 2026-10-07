import java.util.ArrayList;
import java.util.List;

public class ListaDestinos {

    private final List<Destino> lista;

    public ListaDestinos() {
        lista = new ArrayList<>();
    }

    public void anadir(Destino destino) {
        lista.add(destino);
    }

    public List<Destino> getLista() {
        return lista;
    }
}
