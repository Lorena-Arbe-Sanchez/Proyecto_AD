import java.util.ArrayList;
import java.util.List;

public class ListaHoteles {

    private final List<Hotel> lista;

    public ListaHoteles() {
        lista = new ArrayList<>();
    }

    public void anadir(Hotel hotel) {
        lista.add(hotel);
    }

    public List<Hotel> getLista() {
        return lista;
    }
}
