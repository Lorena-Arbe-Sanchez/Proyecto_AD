import com.thoughtworks.xstream.XStream;

import java.io.*;

public class EscribirClientes {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /* ----- Crear fichero '.dat' e insertarle datos ----- */

        // Crear un fichero físico para almacenar la información de los clientes
        File ficheroClientes = new File("FicheroCliente.dat");

        // Crear un objeto de la clase FileOutputStream asociado al fichero físico
        FileOutputStream foClientes = new FileOutputStream(ficheroClientes);

        // Crear un objeto de la clase ObjectOutputStream asociado al objeto anterior
        ObjectOutputStream ooClientes = new ObjectOutputStream(foClientes);

        // Crear 10 objetos de tipo "Cliente"
        Cliente cliente1 = new Cliente(1, "Lorena", "Arbé", "Sánchez", 21, "12345678Z", "688111111", "lorena@gmail.com");
        Cliente cliente2 = new Cliente(2, "Ane", "García", "López", 21, "23456789D", "688222222", "ane@gmail.com");
        Cliente cliente3 = new Cliente(3, "Miren", "Martínez", "Ruiz", 20, "34567890V", "688333333", "miren@gmail.com");
        Cliente cliente4 = new Cliente(4, "Samuel", "Fernández", "Gómez", 23, "45678901H", "688444444", "samuel@gmail.com");
        Cliente cliente5 = new Cliente(5, "Alejandro", "Sánchez", "Pérez", 24, "56789012L", "688555555", "alejandro@gmail.com");
        Cliente cliente6 = new Cliente(6, "Román", "López", "Martín", 20, "67890123C", "688666666", "roman@gmail.com");
        Cliente cliente7 = new Cliente(7, "Sara", "Ruiz", "García", 20, "78901234M", "688777777", "sara@gmail.com");
        Cliente cliente8 = new Cliente(8, "Mikel", "Gómez", "Santos", 21, "89012345Y", "688888888", "mikel@gmail.com");
        Cliente cliente9 = new Cliente(9, "Cristina", "Pérez", "Díaz", 21, "90123456W", "688999999", "cristina@gmail.com");
        Cliente cliente10 = new Cliente(10, "Tania", "Martín", "Navarro", 21, "01234567R", "699111111", "tania@gmail.com");

        // Guardarlos en el objeto ObjectOutputStream
        ooClientes.writeObject(cliente1);
        ooClientes.writeObject(cliente2);
        ooClientes.writeObject(cliente3);
        ooClientes.writeObject(cliente4);
        ooClientes.writeObject(cliente5);
        ooClientes.writeObject(cliente6);
        ooClientes.writeObject(cliente7);
        ooClientes.writeObject(cliente8);
        ooClientes.writeObject(cliente9);
        ooClientes.writeObject(cliente10);

        // Cerrar el flujo de salida
        ooClientes.close();
    }
}