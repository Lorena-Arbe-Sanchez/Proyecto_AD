import java.util.ArrayList;
import java.util.List;

public class ListaClientes {

    private List<Cliente> lista = new ArrayList<Cliente>();

    public ListaClientes() {

    }

    public void anadir(Cliente cliente) {
        lista.add(cliente);
    }

    public List<Cliente> getListaClientes() {
        return lista;
    }
}
