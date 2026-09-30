import java.util.ArrayList;
import java.util.List;

public class ListaClientes {

    private List<Cliente> lista;

    public ListaClientes() {
        lista = new ArrayList<>();
    }

    public void anadir(Cliente cliente) {
        lista.add(cliente);
    }

    public List<Cliente> getListaClientes() {
        return lista;
    }
}
