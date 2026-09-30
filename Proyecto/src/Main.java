import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

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
                
                Teclea el número correspondiente a la opción que deseas:""");
    }

    public static void gestionClientes(Scanner sc) {

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
                    System.out.println("Mostrar clientes");
                    break;

                case 2:
                    System.out.println("Añadir cliente");
                    break;

                case 3:
                    System.out.println("Modificar cliente");
                    break;

                case 4:
                    System.out.println("Eliminar cliente");
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionClientes != 5);
    }

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

    public static void busquedas(Scanner sc) throws IOException {

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
//                    buscarDestino(sc);
                    break;

                case 4:
//                    buscarHotel(sc);
                    break;

                case 5:
//                    buscarReserva(sc);
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

    public static void buscarClientePorDni(Scanner sc) {

        // TODO: Buscar cliente por DNI

    }

    public static void buscarClientePorEmail(Scanner sc) {

        // TODO: Buscar cliente por email

    }

    public static void buscarViaje(Scanner sc) {

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

    public static void buscarViajePorOrigen(Scanner sc) {

        // TODO: Buscar viaje por origen

    }

    public static void buscarViajePorDestino(Scanner sc) {

        // TODO: Buscar viaje por destino

    }

    public static void buscarViajePorFecha(Scanner sc) {

        // TODO: Buscar viaje por fecha

    }

    public static void buscarViajePorTipo(Scanner sc) {

        // TODO: Buscar viaje por tipo de viaje

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