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

        /* ----- Crear fichero '.xml' e insertarle los datos del fichero previo '.dat' ----- */

        // Crear un objeto de la clase FileInputStream asociado al fichero físico
        FileInputStream fiClientes = new FileInputStream(ficheroClientes);

        // Crear un objeto de la clase ObjectInputStream asociado al objeto anterior
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        // Crear objeto de Lista de Clientes
        ListaClientes listaClientes = new ListaClientes();

        // Leer los objetos 'Cliente' del fichero y añadirlos a la lista
        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();
                listaClientes.anadir(cliente);
            }
        } catch (EOFException e) {
            // El final se detecta cuando "readObject()" llega al final del fichero
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de clientes.");
        }

        // Cerrar stream de entrada
        oiClientes.close();

        // Después de obtener todos los clientes, se genera el fichero XML mediante XStream

        try {
            // Crear instancia de la clase XStream
            XStream xstream = new XStream();

            // Cambiar de nombre a las etiquetas XML
            xstream.alias("ListaClientesTotales", ListaClientes.class);

            // También darle un alias a la clase 'Cliente'
            xstream.alias("DatosCliente", Cliente.class);

            // Quitar etiqueta lista (atributo de la clase ListaClientes)
            xstream.addImplicitCollection(ListaClientes.class, "lista");

            // Generar el fichero XML con los datos de la lista de clientes
            FileOutputStream filexml = new FileOutputStream("Clientes.xml");
            xstream.toXML(listaClientes, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// El XML resultante es:
/*
<ListaClientesTotales>
  <DatosCliente>
    <id>1</id>
    <nombre>Lorena</nombre>
    <apellido1>Arbé</apellido1>
    <apellido2>Sánchez</apellido2>
    <edad>21</edad>
    <dni>12345678Z</dni>
    <telefono>688111111</telefono>
    <email>lorena@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>2</id>
    <nombre>Ane</nombre>
    <apellido1>García</apellido1>
    <apellido2>López</apellido2>
    <edad>21</edad>
    <dni>23456789D</dni>
    <telefono>688222222</telefono>
    <email>ane@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>3</id>
    <nombre>Miren</nombre>
    <apellido1>Martínez</apellido1>
    <apellido2>Ruiz</apellido2>
    <edad>20</edad>
    <dni>34567890V</dni>
    <telefono>688333333</telefono>
    <email>miren@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>4</id>
    <nombre>Samuel</nombre>
    <apellido1>Fernández</apellido1>
    <apellido2>Gómez</apellido2>
    <edad>23</edad>
    <dni>45678901H</dni>
    <telefono>688444444</telefono>
    <email>samuel@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>5</id>
    <nombre>Alejandro</nombre>
    <apellido1>Sánchez</apellido1>
    <apellido2>Pérez</apellido2>
    <edad>24</edad>
    <dni>56789012L</dni>
    <telefono>688555555</telefono>
    <email>alejandro@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>6</id>
    <nombre>Román</nombre>
    <apellido1>López</apellido1>
    <apellido2>Martín</apellido2>
    <edad>20</edad>
    <dni>67890123C</dni>
    <telefono>688666666</telefono>
    <email>roman@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>7</id>
    <nombre>Sara</nombre>
    <apellido1>Ruiz</apellido1>
    <apellido2>García</apellido2>
    <edad>20</edad>
    <dni>78901234M</dni>
    <telefono>688777777</telefono>
    <email>sara@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>8</id>
    <nombre>Mikel</nombre>
    <apellido1>Gómez</apellido1>
    <apellido2>Santos</apellido2>
    <edad>21</edad>
    <dni>89012345Y</dni>
    <telefono>688888888</telefono>
    <email>mikel@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>9</id>
    <nombre>Cristina</nombre>
    <apellido1>Pérez</apellido1>
    <apellido2>Díaz</apellido2>
    <edad>21</edad>
    <dni>90123456W</dni>
    <telefono>688999999</telefono>
    <email>cristina@gmail.com</email>
  </DatosCliente>
  <DatosCliente>
    <id>10</id>
    <nombre>Tania</nombre>
    <apellido1>Martín</apellido1>
    <apellido2>Navarro</apellido2>
    <edad>21</edad>
    <dni>01234567R</dni>
    <telefono>699111111</telefono>
    <email>tania@gmail.com</email>
  </DatosCliente>
</ListaClientesTotales>
 */