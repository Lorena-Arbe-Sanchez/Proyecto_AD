import com.thoughtworks.xstream.XStream;

import java.io.*;

public class EscribirDestinos {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // TODO

        /*File ficheroClientes = new File("FicheroCliente.dat");

        FileOutputStream foClientes = new FileOutputStream(ficheroClientes);

        ObjectOutputStream ooClientes = new ObjectOutputStream(foClientes);

        Cliente cliente1 = new Cliente(1, "Lorena", "Arbé", "Sánchez", 21, "12345678Z", "688111111", "lorena@gmail.com");

        ooClientes.writeObject(cliente1);

        ooClientes.close();

        FileInputStream fiClientes = new FileInputStream(ficheroClientes);

        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        ListaClientes listaClientes = new ListaClientes();

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();
                listaClientes.anadir(cliente);
            }
        } catch (EOFException e) {
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de clientes.");
        }

        oiClientes.close();

        try {
            XStream xstream = new XStream();

            xstream.alias("ListaClientesTotales", ListaClientes.class);

            xstream.alias("DatosCliente", Cliente.class);

            xstream.addImplicitCollection(ListaClientes.class, "lista");

            FileOutputStream filexml = new FileOutputStream("Clientes.xml");
            xstream.toXML(listaClientes, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}