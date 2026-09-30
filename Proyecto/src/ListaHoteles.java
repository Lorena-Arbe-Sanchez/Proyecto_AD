import java.util.ArrayList;
import java.util.List;

public class ListaHoteles {

    private List<Hotel> lista;

    public ListaHoteles() {
        lista = new ArrayList<>();
    }

    public void anadir(Hotel hotel) {
        lista.add(hotel);
    }

    public List<Hotel> getLista() {
        return lista;
    }

    public void setLista(List<Hotel> lista) {
        this.lista = lista;
    }
}
