import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        // TODO : Poner funciones en todos los archivos para reutilizar código y/o que quede más limpio + Optimizar

        Scanner sc = new Scanner(System.in);
        int respuesta;

        do {
            // Mostrar menú principal
            mostrarMenuPrincipal();

            respuesta = sc.nextInt();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                    gestionClientes(sc);
                    break;
                case 2:
                    gestionViajes(sc);
                    break;
                case 3:
                    gestionDestinos(sc);
                    break;
                case 4:
                    gestionHoteles(sc);
                    break;
                case 5:
                    gestionReservas(sc);
                    break;
                case 6:
                    busquedas(sc);
                    break;
                case 7:
                    exportarXML(sc);
                    break;
                case 8:
                    // TODO : Poner emojis en los sout o a la hora de hacer la interfaz, que se vea chula
                    System.out.println("Saliendo de EasyTravel...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        while (respuesta != 8);

        sc.close();
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("""
                
                ==========================
                        EASYTRAVEL
                ==========================
                
                1. Gestión de clientes
                2. Gestión de viajes
                3. Gestión de destinos
                4. Gestión de hoteles
                5. Gestión de reservas
                6. Búsquedas
                7. Exportar a XML
                8. Salir
                
                Teclea el número correspondiente a la opción que deseas:"""); // TODO : Poner control de error por si se escribe algo q no sea uno de esos números
    }

    public static void gestionClientes(Scanner sc) throws IOException {

        int opcionClientes;

        do {
            System.out.println("""
                    
                    ==========================
                    GESTIÓN DE CLIENTES
                    ==========================
                    
                    1. Mostrar clientes
                    2. Añadir cliente
                    3. Modificar cliente
                    4. Eliminar cliente
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionClientes = sc.nextInt();
            sc.nextLine();

            switch (opcionClientes) {

                case 1:
                    mostrarClientes();
                    break;

                case 2:
                    anadirCliente(sc);
                    break;

                case 3:
                    modificarCliente(sc);
                    break;

                case 4:
                    eliminarCliente(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionClientes != 5);
    }

    public static void mostrarClientes() throws IOException {

        File fichero = new File("FicheroCliente.dat");
        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null) {
                    cliente.mostrarTodosDatos();
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();
    }

    // TODO : FALTA PROBAR
    public static void anadirCliente(Scanner sc) throws IOException {

        // TODO : Ponerlo automático (que lo ponga como +1 del último q haya en registros)
        System.out.print("\nTeclea el ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Teclea el primer apellido: ");
        String apellido1 = sc.nextLine();

        System.out.print("Teclea el segundo apellido: ");
        String apellido2 = sc.nextLine();

        System.out.print("Teclea la edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el DNI: ");
        String dni = sc.nextLine();

        System.out.print("Teclea el teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Teclea el email: ");
        String email = sc.nextLine();

        Cliente nuevoCliente = new Cliente(
                id,
                nombre,
                apellido1,
                apellido2,
                edad,
                dni,
                telefono,
                email
        );

        File fichero = new File("FicheroCliente.dat");

        ArrayList<Cliente> clientes = new ArrayList<>();

        if (fichero.exists()) {

            FileInputStream fiClientes = new FileInputStream(fichero);
            ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

            try {
                while (true) {
                    Cliente cliente = (Cliente) oiClientes.readObject();

                    if (cliente != null) {
                        clientes.add(cliente);
                    }
                }
            } catch (EOFException e) {
                // Se ha llegado al final del fichero
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            oiClientes.close();
        }

        clientes.add(nuevoCliente);

        FileOutputStream foClientes = new FileOutputStream(fichero);
        ObjectOutputStream ooClientes = new ObjectOutputStream(foClientes);

        for (Cliente cliente : clientes) {
            ooClientes.writeObject(cliente);
        }

        ooClientes.close();

        System.out.println("\nCliente añadido correctamente.");
    }

    // TODO : FALTA PROBAR
    // TODO : Mirar si en los apuntes se hacía así
    public static void modificarCliente(Scanner sc) throws IOException {

        // TODO : Mejor por DNI
        System.out.print("\nTeclea el ID del cliente que quieres modificar: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroCliente.dat");

        ArrayList<Cliente> clientes = new ArrayList<>();

        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null) {

                    if (cliente.getId() == idCliente) {

                        System.out.print("Nuevo nombre: ");
                        cliente.setNombre(sc.nextLine());

                        System.out.print("Nuevo primer apellido: ");
                        cliente.setApellido1(sc.nextLine());

                        System.out.print("Nuevo segundo apellido: ");
                        cliente.setApellido2(sc.nextLine());

                        System.out.print("Nueva edad: ");
                        cliente.setEdad(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Nuevo DNI: ");
                        cliente.setDni(sc.nextLine());

                        System.out.print("Nuevo teléfono: ");
                        cliente.setTelefono(sc.nextLine());

                        System.out.print("Nuevo email: ");
                        cliente.setEmail(sc.nextLine());

                        encontrado = true;
                    }

                    clientes.add(cliente);
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (encontrado) {

            FileOutputStream foClientes = new FileOutputStream(fichero);
            ObjectOutputStream ooClientes = new ObjectOutputStream(foClientes);

            for (Cliente cliente : clientes) {
                ooClientes.writeObject(cliente);
            }

            ooClientes.close();

            System.out.println("\nCliente modificado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún cliente con ese ID.");
        }
    }

    // TODO : FALTA PROBAR
    public static void eliminarCliente(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del cliente que quieres eliminar: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroCliente.dat");

        ArrayList<Cliente> clientes = new ArrayList<>();

        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null) {

                    if (cliente.getId() == idCliente) {
                        encontrado = true;
                    } else {
                        clientes.add(cliente);
                    }
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (encontrado) {

            FileOutputStream foClientes = new FileOutputStream(fichero);
            ObjectOutputStream ooClientes = new ObjectOutputStream(foClientes);

            for (Cliente cliente : clientes) {
                ooClientes.writeObject(cliente);
            }

            ooClientes.close();

            System.out.println("\nCliente eliminado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún cliente con ese ID.");
        }
    }

    // TODO : VOY POR AQUÍ
    public static void gestionViajes(Scanner sc) {

        int opcionViajes;

        do {
            System.out.println("""
                    
                    ==========================
                    GESTIÓN DE VIAJES
                    ==========================
                    
                    1. Mostrar viajes
                    2. Añadir viaje
                    3. Modificar viaje
                    4. Eliminar viaje
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionViajes = sc.nextInt();
            sc.nextLine();

            switch (opcionViajes) {

                case 1:
                    System.out.println("Mostrar viajes");
                    break;

                case 2:
                    System.out.println("Añadir viaje");
                    break;

                case 3:
                    System.out.println("Modificar viaje");
                    break;

                case 4:
                    System.out.println("Eliminar viaje");
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionViajes != 5);
    }

    public static void gestionDestinos(Scanner sc) {

        int opcionDestinos;

        do {
            System.out.println("""
                    
                    ==========================
                    GESTIÓN DE DESTINOS
                    ==========================
                    
                    1. Mostrar destinos
                    2. Añadir destino
                    3. Modificar destino
                    4. Eliminar destino
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionDestinos = sc.nextInt();
            sc.nextLine();

            switch (opcionDestinos) {

                case 1:
                    System.out.println("Mostrar destinos");
                    break;

                case 2:
                    System.out.println("Añadir destino");
                    break;

                case 3:
                    System.out.println("Modificar destino");
                    break;

                case 4:
                    System.out.println("Eliminar destino");
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionDestinos != 5);
    }

    public static void gestionHoteles(Scanner sc) {

        int opcionHoteles;

        do {
            System.out.println("""
                    
                    ==========================
                    GESTIÓN DE HOTELES
                    ==========================
                    
                    1. Mostrar hoteles
                    2. Añadir hotel
                    3. Modificar hotel
                    4. Eliminar hotel
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionHoteles = sc.nextInt();
            sc.nextLine();

            switch (opcionHoteles) {

                case 1:
                    System.out.println("Mostrar hoteles");
                    break;

                case 2:
                    System.out.println("Añadir hotel");
                    break;

                case 3:
                    System.out.println("Modificar hotel");
                    break;

                case 4:
                    System.out.println("Eliminar hotel");
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionHoteles != 5);
    }

    public static void gestionReservas(Scanner sc) {

        int opcionReservas;

        do {
            System.out.println("""
                    
                    ==========================
                    GESTIÓN DE RESERVAS
                    ==========================
                    
                    1. Mostrar reservas
                    2. Añadir reserva
                    3. Modificar reserva
                    4. Eliminar reserva
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionReservas = sc.nextInt();
            sc.nextLine();

            switch (opcionReservas) {

                case 1:
                    System.out.println("Mostrar reservas");
                    break;

                case 2:
                    System.out.println("Añadir reserva");
                    break;

                case 3:
                    System.out.println("Modificar reserva");
                    break;

                case 4:
                    System.out.println("Eliminar reserva");
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionReservas != 5);
    }

    public static void busquedas(Scanner sc) throws IOException, ParseException {

        int opcionBusquedas;

        do {
            System.out.println("""
                    
                    ==========================
                    BÚSQUEDAS
                    ==========================
                    
                    1. Buscar cliente
                    2. Buscar viaje
                    3. Buscar destino
                    4. Buscar hotel
                    5. Buscar reserva
                    6. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionBusquedas = sc.nextInt();
            sc.nextLine();

            switch (opcionBusquedas) {

                case 1:
                    buscarCliente(sc);
                    break;

                case 2:
                    buscarViaje(sc);
                    break;

                case 3:
                    buscarDestino(sc);
                    break;

                case 4:
                    buscarHotel(sc);
                    break;

                case 5:
                    buscarReserva(sc);
                    break;

                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionBusquedas != 6);
    }

    public static void buscarCliente(Scanner sc) throws IOException {

        int opcionCliente;

        do {
            System.out.println("""
                    
                    ==========================
                         BUSCAR CLIENTE
                    ==========================
                    
                    1. Buscar por ID
                    2. Buscar por DNI
                    3. Buscar por email
                    4. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionCliente = sc.nextInt();
            sc.nextLine();

            switch (opcionCliente) {
                case 1:
                    buscarClientePorId(sc);
                    break;

                case 2:
                    buscarClientePorDni(sc);
                    break;

                case 3:
                    buscarClientePorEmail(sc);
                    break;

                case 4:
                    System.out.println("Volviendo al menú de búsquedas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        while (opcionCliente != 4);
    }

    public static void buscarClientePorId(Scanner sc) throws IOException {

        // Preguntar por el ID
        System.out.print("\nTeclea el ID: ");
        int idCliente = sc.nextInt();

        File fichero = new File("FicheroCliente.dat");
        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null && cliente.getId() == idCliente) {
                    cliente.mostrarTodosDatos();
                    encontrado = true;
                    break;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún cliente con ese ID.");
        }
    }

    public static void buscarClientePorDni(Scanner sc) throws IOException {

        System.out.print("\nTeclea el DNI: ");
        String dniCliente = sc.nextLine();

        File fichero = new File("FicheroCliente.dat");
        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                // "equalsIgnoreCase" permite comparar dos textos sin distinguir entre mayúsculas y minúsculas
                if (cliente != null && cliente.getDni().equalsIgnoreCase(dniCliente)) {
                    cliente.mostrarTodosDatos();
                    encontrado = true;
                    break;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún cliente con ese DNI.");
        }
    }

    public static void buscarClientePorEmail(Scanner sc) throws IOException {

        System.out.print("\nTeclea el email: ");
        String emailCliente = sc.nextLine();

        File fichero = new File("FicheroCliente.dat");
        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null && cliente.getEmail().equalsIgnoreCase(emailCliente)) {
                    cliente.mostrarTodosDatos();
                    encontrado = true;
                    break;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún cliente con ese email.");
        }
    }

    public static void buscarViaje(Scanner sc) throws IOException, ParseException {

        int opcionViaje;

        do {
            System.out.println("""
                    
                    ==========================
                          BUSCAR VIAJE
                    ==========================
                    
                    1. Buscar por origen
                    2. Buscar por destino
                    3. Buscar por fecha
                    4. Buscar por tipo de viaje
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionViaje = sc.nextInt();
            sc.nextLine();

            switch (opcionViaje) {
                case 1:
                    buscarViajePorOrigen(sc);
                    break;

                case 2:
                    buscarViajePorDestino(sc);
                    break;

                case 3:
                    buscarViajePorFecha(sc);
                    break;

                case 4:
                    buscarViajePorTipo(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú de búsquedas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionViaje != 5);
    }

    public static void buscarViajePorOrigen(Scanner sc) throws IOException {

        System.out.print("\nTeclea el origen: ");
        String origen = sc.nextLine();

        File fichero = new File("FicheroViaje.dat");
        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        boolean encontrado = false;

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null && viaje.getOrigen().equalsIgnoreCase(origen)) {
                    viaje.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con ese origen.");
        }
    }

    public static void buscarViajePorDestino(Scanner sc) throws IOException {

        System.out.print("\nTeclea el destino: ");
        String ciudad = sc.nextLine();

        File fichero = new File("FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getCiudad().equalsIgnoreCase(ciudad)) {
                    int idDestino = destino.getId();

                    FileInputStream fiViajes = new FileInputStream("FicheroViaje.dat");
                    ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

                    try {
                        while (true) {
                            Viaje viaje = (Viaje) oiViajes.readObject();

                            if (viaje != null && viaje.getIdDestino() == idDestino) {
                                viaje.mostrarTodosDatos();
                                encontrado = true;
                            }
                        }
                    } catch (EOFException e) {
                        // Se ha llegado al final del fichero de viajes
                    }

                    oiViajes.close();

                    // Salir del bucle porque ya se ha encontrado el destino y se han buscado sus viajes
                    break;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero de destinos
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con ese destino.");
        }
    }

    // TODO : Control de error de fecha incorrecta o que no sigue el patrón + De texto no numérico
    public static void buscarViajePorFecha(Scanner sc) throws IOException, ParseException {

        System.out.print("\nTeclea la fecha (dd/MM/yyyy): ");
        String fechaTexto = sc.nextLine();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = formatoFecha.parse(fechaTexto);

        File fichero = new File("FicheroViaje.dat");
        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        boolean encontrado = false;

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null && viaje.getFechaSalida().equals(fecha)) {
                    viaje.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con esa fecha.");
        }
    }

    public static void buscarViajePorTipo(Scanner sc) throws IOException {

        System.out.print("\nTeclea el tipo de viaje: ");
        String tipoViaje = sc.nextLine();

        File fichero = new File("FicheroViaje.dat");
        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        boolean encontrado = false;

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null && viaje.getTipoViaje().equalsIgnoreCase(tipoViaje)) {
                    viaje.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con ese tipo.");
        }
    }

    public static void buscarDestino(Scanner sc) throws IOException {

        int opcionDestino;

        do {
            System.out.println("""
                    
                    ==========================
                    BUSCAR DESTINO
                    ==========================
                    
                    1. Buscar por ciudad
                    2. Buscar por país
                    3. Buscar por tipo de destino
                    4. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionDestino = sc.nextInt();
            sc.nextLine();

            switch (opcionDestino) {
                case 1:
                    buscarDestinoPorCiudad(sc);
                    break;

                case 2:
                    buscarDestinoPorPais(sc);
                    break;

                case 3:
                    buscarDestinoPorTipo(sc);
                    break;

                case 4:
                    System.out.println("Volviendo al menú de búsquedas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionDestino != 4);
    }

    public static void buscarDestinoPorCiudad(Scanner sc) throws IOException {

        System.out.print("\nTeclea la ciudad: ");
        String ciudad = sc.nextLine();

        File fichero = new File("FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getCiudad().equalsIgnoreCase(ciudad)) {
                    destino.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún destino con esa ciudad.");
        }
    }

    public static void buscarDestinoPorPais(Scanner sc) throws IOException {

        System.out.print("\nTeclea el país: ");
        String pais = sc.nextLine();

        File fichero = new File("FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getPais().equalsIgnoreCase(pais)) {
                    destino.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún destino en ese país.");
        }
    }

    public static void buscarDestinoPorTipo(Scanner sc) throws IOException {

        System.out.print("\nTeclea el tipo de destino: ");
        String tipoDestino = sc.nextLine();

        File fichero = new File("FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getTipoDestino().equalsIgnoreCase(tipoDestino)) {
                    destino.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún destino de ese tipo.");
        }
    }

    public static void buscarHotel(Scanner sc) throws IOException {

        int opcionHotel;

        do {
            System.out.println("""
                    
                    ==========================
                    BUSCAR HOTEL
                    ==========================
                    
                    1. Buscar por estrellas
                    2. Buscar por precio máximo
                    3. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionHotel = sc.nextInt();
            sc.nextLine();

            switch (opcionHotel) {
                case 1:
                    buscarHotelPorEstrellas(sc);
                    break;

                case 2:
                    buscarHotelPorPrecio(sc);
                    break;

                case 3:
                    System.out.println("Volviendo al menú de búsquedas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionHotel != 3);
    }

    public static void buscarHotelPorEstrellas(Scanner sc) throws IOException {

        System.out.print("\nTeclea el número de estrellas: ");
        int estrellas = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroHotel.dat");
        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        boolean encontrado = false;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null && hotel.getEstrellas() == estrellas) {
                    hotel.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiHoteles.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún hotel con ese número de estrellas.");
        }
    }

    public static void buscarHotelPorPrecio(Scanner sc) throws IOException {

        System.out.print("\nTeclea el precio máximo por noche: ");
        double precioMaximo = sc.nextDouble();
        sc.nextLine();

        File fichero = new File("FicheroHotel.dat");
        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        boolean encontrado = false;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null && hotel.getPrecioNoche() <= precioMaximo) {
                    hotel.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiHoteles.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún hotel con ese precio máximo.");
        }
    }

    public static void buscarReserva(Scanner sc) throws IOException {

        int opcionReserva;

        do {
            System.out.println("""
                    
                    ==========================
                    BUSCAR RESERVA
                    ==========================
                    
                    1. Buscar por ID
                    2. Buscar por cliente
                    3. Buscar por viaje
                    4. Buscar por estado
                    5. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionReserva = sc.nextInt();
            sc.nextLine();

            switch (opcionReserva) {
                case 1:
                    buscarReservaPorId(sc);
                    break;

                case 2:
                    buscarReservaPorCliente(sc);
                    break;

                case 3:
                    buscarReservaPorViaje(sc);
                    break;

                case 4:
                    buscarReservaPorEstado(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú de búsquedas...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionReserva != 5);
    }

    public static void buscarReservaPorId(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID de la reserva: ");
        int idReserva = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroReserva.dat");
        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null && reserva.getId() == idReserva) {
                    reserva.mostrarTodosDatos();
                    encontrado = true;
                    break;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese ID.");
        }
    }

    // TODO : Hacerlo por el DNI del cliente (habrá que hacer doble búsqueda como cuando lo del destino del viaje)
    public static void buscarReservaPorCliente(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroReserva.dat");
        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null && reserva.getIdCliente() == idCliente) {
                    reserva.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva para ese cliente.");
        }
    }

    public static void buscarReservaPorViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje: ");
        int idViaje = sc.nextInt();
        sc.nextLine();

        File fichero = new File("FicheroReserva.dat");
        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null && reserva.getIdViaje() == idViaje) {
                    reserva.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva para ese viaje.");
        }
    }

    public static void buscarReservaPorEstado(Scanner sc) throws IOException {

        System.out.print("\nTeclea el estado de la reserva: ");
        String estado = sc.nextLine();

        File fichero = new File("FicheroReserva.dat");
        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null && reserva.getEstado().equalsIgnoreCase(estado)) {
                    reserva.mostrarTodosDatos();
                    encontrado = true;
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese estado.");
        }
    }

    public static void exportarXML(Scanner sc) {

        int opcionXML;

        do {
            System.out.println("""
                    
                    ==========================
                    EXPORTAR A XML
                    ==========================
                    
                    1. Exportar clientes
                    2. Exportar viajes
                    3. Exportar destinos
                    4. Exportar hoteles
                    5. Exportar reservas
                    6. Exportar todo
                    7. Volver
                    
                    Teclea el número correspondiente a la opción que deseas:""");

            opcionXML = sc.nextInt();
            sc.nextLine();

            switch (opcionXML) {

                case 1:
                    System.out.println("Exportar clientes");
                    break;

                case 2:
                    System.out.println("Exportar viajes");
                    break;

                case 3:
                    System.out.println("Exportar destinos");
                    break;

                case 4:
                    System.out.println("Exportar hoteles");
                    break;

                case 5:
                    System.out.println("Exportar reservas");
                    break;

                case 6:
                    System.out.println("Exportar todo");
                    break;

                case 7:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionXML != 7);
    }
}