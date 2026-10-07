import java.util.ArrayList;
import java.util.List;

public class ListaClientes {

    private final List<Cliente> lista;

    public ListaClientes() {
        lista = new ArrayList<>();
    }

    public void anadir(Cliente cliente) {
        lista.add(cliente);
    }
}
