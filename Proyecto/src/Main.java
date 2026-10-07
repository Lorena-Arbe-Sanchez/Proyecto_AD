import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        // TODO : Poner funciones en todos los archivos para reutilizar código y/o que quede más limpio + Optimizar

        Scanner sc = new Scanner(System.in);
        int respuesta;

        do {
            // Mostrar menú principal
            mostrarMenuPrincipal();

            respuesta = leerInt(sc);

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
                
                Teclea el número correspondiente a la opción que deseas:""");
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

            opcionClientes = leerInt(sc);

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

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

        ArrayList<Cliente> clientes = new ArrayList<>();

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los clientes.");
        }

        oiClientes.close();

        System.out.println("\nNúmero de clientes: " + clientes.size());

        for (Cliente cliente : clientes) {
            cliente.mostrarTodosDatos();
        }
    }

    public static void anadirCliente(Scanner sc) throws IOException {

        // El ID será automático en las funciones de añadir objetos a las clases
        // Se obtendrá el último ID en el fichero de clientes y se guardará el nuevo registro como ese ID +1
        int id = obtenerUltimoIdCliente() + 1;

        String nombre = leerTexto(sc, "el nombre: ");

        String apellido1 = leerTexto(sc, "el primer apellido: ");

        String apellido2 = leerTexto(sc, "el segundo apellido: ");

        System.out.print("Teclea la edad: ");
        int edad = leerInt(sc);

        // Comprobar si el DNI proporcionado tiene el patrón correcto
        String dni = leerDni(sc);

        String telefono = leerTelefono(sc);

        String email = leerEmail(sc);

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

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

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
                // Fin del fichero
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
        System.out.println("\nEl ID asignado al nuevo cliente es: " + id);
    }

    // TODO : A la hora de poner los datos a modificar --> Hacer que aparezcan los datos para poder cambiarles poco (que aparezcan ya al teclear "3")
    public static void modificarCliente(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del cliente que quieres modificar: ");
        int idCliente = leerInt(sc);

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

        ArrayList<Cliente> clientes = new ArrayList<>();

        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        boolean encontrado = false;

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null) {

                    if (cliente.getId() == idCliente) {

                        String nombre = leerTexto(sc, "el nuevo nombre");
                        cliente.setNombre(nombre);

                        String primerApellido = leerTexto(sc, "el nuevo primer apellido");
                        cliente.setApellido1(primerApellido);

                        String segundoApellido = leerTexto(sc, "el nuevo segundo apellido");
                        cliente.setApellido2(segundoApellido);

                        System.out.print("Nueva edad: ");
                        int edad = leerInt(sc);
                        cliente.setEdad(edad);

                        String dni = leerDni(sc);
                        cliente.setDni(dni);

                        String telefono = leerTexto(sc, "el nuevo teléfono");
                        cliente.setTelefono(telefono);

                        String email = leerEmail(sc);
                        cliente.setEmail(email);

                        encontrado = true;
                    }

                    clientes.add(cliente);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
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

    public static void eliminarCliente(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del cliente que quieres eliminar: ");
        int idCliente = leerInt(sc);

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

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
            // Fin del fichero
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

    public static void gestionViajes(Scanner sc) throws IOException {

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

            opcionViajes = leerInt(sc);

            switch (opcionViajes) {

                case 1:
                    mostrarViajes();
                    break;

                case 2:
                    anadirViaje(sc);
                    break;

                case 3:
                    modificarViaje(sc);
                    break;

                case 4:
                    eliminarViaje(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionViajes != 5);
    }

    public static void mostrarViajes() throws IOException {

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

        ArrayList<Viaje> viajes = new ArrayList<>();

        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null) {
                    viajes.add(viaje);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los viajes.");
        }

        oiViajes.close();

        System.out.println("\nNúmero de viajes: " + viajes.size());

        for (Viaje viaje : viajes) {
            viaje.mostrarTodosDatos();
        }
    }

    public static void anadirViaje(Scanner sc) throws IOException {

        int id = obtenerUltimoIdViaje() + 1;

        System.out.print("Teclea el ID del destino: ");
        int idDestino = leerInt(sc);

        if (!existeDestino(idDestino)) {
            System.out.println("\nError: no existe ningún destino con ese ID.");
            return;
        }

        System.out.print("¿Quieres añadir hotel? (s/n): ");
        String respuesta = sc.nextLine();

        Integer idHotel = null;

        if (respuesta.equalsIgnoreCase("s")) {

            System.out.print("Teclea el ID del hotel: ");
            idHotel = leerInt(sc);

            if (!existeHotel(idHotel)) {
                System.out.println("\nError: no existe ningún hotel con ese ID.");
                return;
            }
        }

        String origen = leerTexto(sc, "el origen: ");

        Date fechaSalida = leerFecha(sc, " de salida");

        Date fechaRegreso = leerFecha(sc, " de regreso");

        System.out.print("Teclea el precio: ");
        double precio = leerDouble(sc);

        System.out.print("Teclea el número de plazas totales: ");
        int plazasTotales = leerInt(sc);

        System.out.print("Teclea el número de plazas disponibles: ");
        int plazasDisponibles = leerInt(sc);

        String tipoViaje = leerTipoViaje(sc);

        Viaje nuevoViaje = new Viaje(
                id,
                idDestino,
                idHotel,
                origen,
                fechaSalida,
                fechaRegreso,
                precio,
                plazasTotales,
                plazasDisponibles,
                tipoViaje
        );

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

        ArrayList<Viaje> viajes = new ArrayList<>();

        if (fichero.exists()) {

            FileInputStream fiViajes = new FileInputStream(fichero);
            ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

            try {
                while (true) {
                    Viaje viaje = (Viaje) oiViajes.readObject();

                    if (viaje != null) {
                        viajes.add(viaje);
                    }
                }
            } catch (EOFException e) {
                // Fin del fichero
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            oiViajes.close();
        }

        viajes.add(nuevoViaje);

        FileOutputStream foViajes = new FileOutputStream(fichero);
        ObjectOutputStream ooViajes = new ObjectOutputStream(foViajes);

        for (Viaje viaje : viajes) {
            ooViajes.writeObject(viaje);
        }

        ooViajes.close();

        System.out.println("\nViaje añadido correctamente.");
        System.out.println("\nEl ID asignado al nuevo viaje es: " + id);
    }

    public static void modificarViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje que quieres modificar: ");
        int idViaje = leerInt(sc);

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

        ArrayList<Viaje> viajes = new ArrayList<>();

        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        boolean encontrado = false;

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null) {

                    if (viaje.getId() == idViaje) {

                        System.out.print("Nuevo ID del destino: ");
                        int idDestino = leerInt(sc);

                        // TODO : Hacer bien "existeDestino" antes de guardarlo (con bucle como abajo en "existeHotel")
                        if (!existeDestino(idDestino)) {
                            System.out.println("\nError: no existe ese destino.");
                            return;
                        }

                        viaje.setIdDestino(idDestino);

                        int idHotel;

                        while (true) {
                            System.out.print("Nuevo ID del hotel (0 si no hay alojamiento): ");
                            idHotel = leerInt(sc);

                            if (idHotel == 0) {
                                viaje.setIdHotel(null);
                            } else {
                                if (existeHotel(idHotel)) {
                                    break;
                                }

                                System.out.println("\nError: no existe ese hotel. Vuelve a introducir el ID.");
                            }
                        }

                        viaje.setIdHotel(idHotel);

                        String origen = leerTexto(sc, "el nuevo origen");
                        viaje.setOrigen(origen);

                        Date fechaSalida = leerFecha(sc, " de salida");
                        viaje.setFechaSalida(fechaSalida);

                        Date fechaRegreso = leerFecha(sc, " de regreso");
                        viaje.setFechaRegreso(fechaRegreso);

                        System.out.print("Nuevo precio: ");
                        double precio = leerDouble(sc);
                        viaje.setPrecio(precio);

                        System.out.print("Nuevo número de plazas totales: ");
                        int plazasTotales = leerInt(sc);
                        viaje.setPlazasTotales(plazasTotales);

                        System.out.print("Nuevo número de plazas disponibles: ");
                        int plazasDisponibles = leerInt(sc);
                        viaje.setPlazasDisponibles(plazasDisponibles);

                        String tipo = leerTipoViaje(sc);
                        viaje.setTipoViaje(tipo);

                        encontrado = true;
                    }

                    viajes.add(viaje);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (encontrado) {

            FileOutputStream foViajes = new FileOutputStream(fichero);
            ObjectOutputStream ooViajes = new ObjectOutputStream(foViajes);

            for (Viaje viaje : viajes) {
                ooViajes.writeObject(viaje);
            }

            ooViajes.close();

            System.out.println("\nViaje modificado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún viaje con ese ID.");
        }
    }

    public static void eliminarViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje que quieres eliminar: ");
        int idViaje = leerInt(sc);

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

        ArrayList<Viaje> viajes = new ArrayList<>();

        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        boolean encontrado = false;

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null) {

                    if (viaje.getId() == idViaje) {
                        encontrado = true;
                    } else {
                        viajes.add(viaje);
                    }
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (encontrado) {

            FileOutputStream foViajes = new FileOutputStream(fichero);
            ObjectOutputStream ooViajes = new ObjectOutputStream(foViajes);

            for (Viaje viaje : viajes) {
                ooViajes.writeObject(viaje);
            }

            ooViajes.close();

            System.out.println("\nViaje eliminado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún viaje con ese ID.");
        }
    }

    public static void gestionDestinos(Scanner sc) throws IOException, ClassNotFoundException {

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

            opcionDestinos = leerInt(sc);

            switch (opcionDestinos) {

                case 1:
                    mostrarDestinos();
                    break;

                case 2:
                    anadirDestino(sc);
                    break;

                case 3:
                    modificarDestino(sc);
                    break;

                case 4:
                    eliminarDestino(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionDestinos != 5);
    }

    public static void mostrarDestinos() throws IOException, ClassNotFoundException {

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        ArrayList<Destino> destinos = new ArrayList<>();

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {
                    destinos.add(destino);
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los destinos.");
        }

        oiDestinos.close();

        System.out.println("\nNúmero de destinos: " + destinos.size());

        for (Destino destino : destinos) {
            destino.mostrarTodosDatos();
        }
    }

    public static void anadirDestino(Scanner sc) throws IOException, ClassNotFoundException {

        int id = obtenerUltimoIdDestino() + 1;

        String ciudad = leerTexto(sc, "la ciudad: ");

        String pais = leerTexto(sc, "el país: ");

        String descripcion = leerTexto(sc, "la descripción: ");

        String tipoDestino = leerTipoDestino(sc);

        String idioma = leerTexto(sc, "el idioma: ");

        String moneda = leerTexto(sc, "la moneda: ");

        String imagenUrl = leerTexto(sc, "la URL de la imagen: ");

        Destino nuevoDestino = new Destino(
                id,
                ciudad,
                pais,
                descripcion,
                tipoDestino,
                idioma,
                moneda,
                imagenUrl
        );

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        ArrayList<Destino> destinos = new ArrayList<>();

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {
                    destinos.add(destino);
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        destinos.add(nuevoDestino);

        FileOutputStream foDestinos = new FileOutputStream(fichero);
        ObjectOutputStream ooDestinos = new ObjectOutputStream(foDestinos);

        for (Destino destino : destinos) {
            ooDestinos.writeObject(destino);
        }

        ooDestinos.close();

        System.out.println("\nDestino añadido correctamente.");
        System.out.println("\nEl ID asignado al nuevo destino es: " + id);
    }

    public static void modificarDestino(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del destino que quieres modificar: ");
        int idDestino = leerInt(sc);

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        ListaDestinos listaDestinos = new ListaDestinos();
        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {

                    if (destino.getId() == idDestino) {

                        String ciudad = leerTexto(sc, "la nueva ciudad");
                        destino.setCiudad(ciudad);

                        String pais = leerTexto(sc, "el nuevo país");
                        destino.setPais(pais);

                        String descripcion = leerTexto(sc, "la nueva descripción");
                        destino.setDescripcion(descripcion);

                        String tipo = leerTipoDestino(sc);
                        destino.setTipoDestino(tipo);

                        String idioma = leerTexto(sc, "el nuevo idioma");
                        destino.setIdioma(idioma);

                        String moneda = leerTexto(sc, "la nueva moneda");
                        destino.setMoneda(moneda);

                        String url = leerTexto(sc, "la nueva URL de imagen");
                        destino.setImagenUrl(url);

                        encontrado = true;
                    }

                    listaDestinos.anadir(destino);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiDestinos.close();

        if (encontrado) {

            FileOutputStream foDestinos = new FileOutputStream(fichero);
            ObjectOutputStream ooDestinos = new ObjectOutputStream(foDestinos);

            for (Destino destino : listaDestinos.getLista()) {
                ooDestinos.writeObject(destino);
            }

            ooDestinos.close();

            System.out.println("\nDestino modificado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún destino con ese ID.");
        }
    }

    public static void eliminarDestino(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del destino que quieres eliminar: ");
        int idDestino = leerInt(sc);

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        ListaDestinos listaDestinos = new ListaDestinos();
        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {

                    if (destino.getId() == idDestino) {
                        encontrado = true;
                    } else {
                        listaDestinos.anadir(destino);
                    }
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiDestinos.close();

        if (encontrado) {

            FileOutputStream foDestinos = new FileOutputStream(fichero);
            ObjectOutputStream ooDestinos = new ObjectOutputStream(foDestinos);

            for (Destino destino : listaDestinos.getLista()) {
                ooDestinos.writeObject(destino);
            }

            ooDestinos.close();

            System.out.println("\nDestino eliminado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún destino con ese ID.");
        }
    }

    public static void gestionHoteles(Scanner sc) throws IOException, ClassNotFoundException {

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

            opcionHoteles = leerInt(sc);

            switch (opcionHoteles) {

                case 1:
                    mostrarHoteles();
                    break;

                case 2:
                    anadirHotel(sc);
                    break;

                case 3:
                    modificarHotel(sc);
                    break;

                case 4:
                    eliminarHotel(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionHoteles != 5);
    }

    public static void mostrarHoteles() throws IOException, ClassNotFoundException {

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

        ArrayList<Hotel> hoteles = new ArrayList<>();

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {
                    hoteles.add(hotel);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los hoteles.");
        }

        oiHoteles.close();

        System.out.println("\nNúmero de hoteles: " + hoteles.size());

        for (Hotel hotel : hoteles) {
            hotel.mostrarTodosDatos();
        }
    }

    public static void anadirHotel(Scanner sc) throws IOException {

        int id = obtenerUltimoIdHotel() + 1;

        String nombre = leerTexto(sc, "el nombre: ");

        System.out.print("Teclea el número de estrellas: ");
        int estrellas = leerEstrellas(sc);

        String direccion = leerTexto(sc, "la dirección: ");

        System.out.print("Teclea el precio por noche: ");
        double precioNoche = leerDouble(sc);

        String servicios = leerTexto(sc, "los servicios: ");

        Hotel nuevoHotel = new Hotel(
                id,
                nombre,
                estrellas,
                direccion,
                precioNoche,
                servicios
        );

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

        ArrayList<Hotel> hoteles = new ArrayList<>();

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {
                    hoteles.add(hotel);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiHoteles.close();

        hoteles.add(nuevoHotel);

        FileOutputStream foHoteles = new FileOutputStream(fichero);
        ObjectOutputStream ooHoteles = new ObjectOutputStream(foHoteles);

        for (Hotel hotel : hoteles) {
            ooHoteles.writeObject(hotel);
        }

        ooHoteles.close();

        System.out.println("\nHotel añadido correctamente.");
        System.out.println("\nEl ID asignado al nuevo hotel es: " + id);
    }

    public static void modificarHotel(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del hotel que quieres modificar: ");
        int idHotel = leerInt(sc);

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        ListaHoteles listaHoteles = new ListaHoteles();
        boolean encontrado = false;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {

                    if (hotel.getId() == idHotel) {

                        String nombre = leerTexto(sc, "el nuevo nombre");
                        hotel.setNombre(nombre);

                        System.out.print("Nuevo número de estrellas: ");
                        int estrellas = leerEstrellas(sc);
                        hotel.setEstrellas(estrellas);

                        String direccion = leerTexto(sc, "la nueva dirección");
                        hotel.setDireccion(direccion);

                        System.out.print("Nuevo precio por noche: ");
                        double precio = leerDouble(sc);
                        hotel.setPrecioNoche(precio);

                        String servicios = leerTexto(sc, "los nuevos servicios");
                        hotel.setServicios(servicios);

                        encontrado = true;
                    }

                    listaHoteles.anadir(hotel);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiHoteles.close();

        if (encontrado) {

            FileOutputStream foHoteles = new FileOutputStream(fichero);
            ObjectOutputStream ooHoteles = new ObjectOutputStream(foHoteles);

            for (Hotel hotel : listaHoteles.getLista()) {
                ooHoteles.writeObject(hotel);
            }

            ooHoteles.close();

            System.out.println("\nHotel modificado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún hotel con ese ID.");
        }
    }

    public static void eliminarHotel(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del hotel que quieres eliminar: ");
        int idHotel = leerInt(sc);

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        ListaHoteles listaHoteles = new ListaHoteles();
        boolean encontrado = false;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {

                    if (hotel.getId() == idHotel) {
                        encontrado = true;
                    } else {
                        listaHoteles.anadir(hotel);
                    }
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiHoteles.close();

        if (encontrado) {

            FileOutputStream foHoteles = new FileOutputStream(fichero);
            ObjectOutputStream ooHoteles = new ObjectOutputStream(foHoteles);

            for (Hotel hotel : listaHoteles.getLista()) {
                ooHoteles.writeObject(hotel);
            }

            ooHoteles.close();

            System.out.println("\nHotel eliminado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún hotel con ese ID.");
        }
    }

    public static void gestionReservas(Scanner sc) throws IOException, ClassNotFoundException {

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

            opcionReservas = leerInt(sc);

            switch (opcionReservas) {

                case 1:
                    mostrarReservas();
                    break;

                case 2:
                    anadirReserva(sc);
                    break;

                case 3:
                    modificarReserva(sc);
                    break;

                case 4:
                    eliminarReserva(sc);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionReservas != 5);
    }

    public static void mostrarReservas() throws IOException, ClassNotFoundException {

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

        ArrayList<Reserva> reservas = new ArrayList<>();

        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null) {
                    reservas.add(reserva);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer las reservas.");
        }

        oiReservas.close();

        System.out.println("\nNúmero de reservas: " + reservas.size());

        for (Reserva reserva : reservas) {
            reserva.mostrarTodosDatos();
        }
    }

    public static void anadirReserva(Scanner sc) throws IOException {

        int id = obtenerUltimoIdReserva() + 1;

        System.out.print("Teclea el ID del cliente: ");
        int idCliente = leerInt(sc);

        if (!existeCliente(idCliente)) {
            System.out.println("\nError: no existe ningún cliente con ese ID.");
            return;
        }

        System.out.print("Teclea el ID del viaje: ");
        int idViaje = leerInt(sc);

        if (!existeViaje(idViaje)) {
            System.out.println("\nError: no existe ningún viaje con ese ID.");
            return;
        }

        Date fechaReserva = leerFecha(sc, " de reserva");

        System.out.print("Teclea el número de personas: ");
        int numeroPersonas = leerInt(sc);

        System.out.print("Teclea el precio total: ");
        double precioTotal = leerDouble(sc);

        String estado = leerEstadoReserva(sc);

        Reserva nuevaReserva = new Reserva(
                id,
                idCliente,
                idViaje,
                fechaReserva,
                numeroPersonas,
                precioTotal,
                estado
        );

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

        ArrayList<Reserva> reservas = new ArrayList<>();

        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null) {
                    reservas.add(reserva);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        reservas.add(nuevaReserva);

        FileOutputStream foReservas = new FileOutputStream(fichero);
        ObjectOutputStream ooReservas = new ObjectOutputStream(foReservas);

        for (Reserva reserva : reservas) {
            ooReservas.writeObject(reserva);
        }

        ooReservas.close();

        System.out.println("\nReserva añadida correctamente.");
        System.out.println("\nEl ID asignado a la nueva reserva es: " + id);
    }

    public static void modificarReserva(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID de la reserva que quieres modificar: ");
        int idReserva = leerInt(sc);

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        ListaReservas listaReservas = new ListaReservas();
        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null) {

                    if (reserva.getId() == idReserva) {

                        System.out.print("Nuevo ID del cliente: ");
                        int idCliente = leerInt(sc);
                        // TODO : Hacer "existeCliente" antes de guardarlo
                        reserva.setIdCliente(idCliente);

                        System.out.print("Nuevo ID del viaje: ");
                        int idViaje = leerInt(sc);
                        // TODO : Hacer "existeViaje" antes de guardarlo
                        reserva.setIdViaje(idViaje);

                        Date fechaReserva = leerFecha(sc, " de reserva");
                        reserva.setFechaReserva(fechaReserva);

                        System.out.print("Nuevo número de personas: ");
                        int numero = leerInt(sc);
                        reserva.setNumeroPersonas(numero);

                        System.out.print("Nuevo precio total: ");
                        double precio = leerDouble(sc);
                        reserva.setPrecioTotal(precio);

                        String estado = leerTexto(sc, "el nuevo estado");
                        reserva.setEstado(estado);

                        encontrado = true;
                    }

                    listaReservas.anadir(reserva);
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiReservas.close();

        if (encontrado) {

            FileOutputStream foReservas = new FileOutputStream(fichero);
            ObjectOutputStream ooReservas = new ObjectOutputStream(foReservas);

            for (Reserva reserva : listaReservas.getLista()) {
                ooReservas.writeObject(reserva);
            }

            ooReservas.close();

            System.out.println("\nReserva modificada correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese ID.");
        }
    }

    public static void eliminarReserva(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID de la reserva que quieres eliminar: ");
        int idReserva = leerInt(sc);

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        ListaReservas listaReservas = new ListaReservas();
        boolean encontrado = false;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null) {

                    if (reserva.getId() == idReserva) {
                        encontrado = true;
                    } else {
                        listaReservas.anadir(reserva);
                    }
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        }

        oiReservas.close();

        if (encontrado) {

            FileOutputStream foReservas = new FileOutputStream(fichero);
            ObjectOutputStream ooReservas = new ObjectOutputStream(foReservas);

            for (Reserva reserva : listaReservas.getLista()) {
                ooReservas.writeObject(reserva);
            }

            ooReservas.close();

            System.out.println("\nReserva eliminada correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese ID.");
        }
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

            opcionBusquedas = leerInt(sc);

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

            opcionCliente = leerInt(sc);

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
        int idCliente = leerInt(sc);

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún cliente con ese ID.");
        }
    }

    public static void buscarClientePorDni(Scanner sc) throws IOException {

        String dniCliente = leerDni(sc);

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiClientes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún cliente con ese DNI.");
        }
    }

    public static void buscarClientePorEmail(Scanner sc) throws IOException {

        String emailCliente = leerEmail(sc);

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

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
            // Fin del fichero
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

            opcionViaje = leerInt(sc);

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

        String origen = leerTexto(sc, "el origen: ");

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con ese origen.");
        }
    }

    // TODO : Poner de comentario q en esta función primero se busca el id del nombre del destino y luego se buscan sus viajes
    public static void buscarViajePorDestino(Scanner sc) throws IOException {

        String ciudad = leerTexto(sc, "el destino: ");

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getCiudad().equalsIgnoreCase(ciudad)) {
                    int idDestino = destino.getId();

                    File ficheroViaje = new File("datos/dat/FicheroViaje.dat");

                    if (!ficheroViaje.exists()) {
                        System.out.println("\nNo existe el fichero de viajes.");
                        return;
                    }

                    FileInputStream fiViajes = new FileInputStream(ficheroViaje);
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

    public static void buscarViajePorFecha(Scanner sc) throws IOException, ParseException {

        Date fecha = leerFecha(sc, " de salida");

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún viaje con esa fecha.");
        }
    }

    public static void buscarViajePorTipo(Scanner sc) throws IOException {

        String tipoViaje = leerTipoViaje(sc);

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

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
            // Fin del fichero
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

            opcionDestino = leerInt(sc);

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

        String ciudad = leerTexto(sc, "la ciudad: ");

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún destino con esa ciudad.");
        }
    }

    public static void buscarDestinoPorPais(Scanner sc) throws IOException {

        String pais = leerTexto(sc, "el país: ");

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiDestinos.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ningún destino en ese país.");
        }
    }

    public static void buscarDestinoPorTipo(Scanner sc) throws IOException {

        String tipoDestino = leerTipoDestino(sc);

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

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
            // Fin del fichero
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

            opcionHotel = leerInt(sc);

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
        int estrellas = leerEstrellas(sc);

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

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
            // Fin del fichero
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
        double precioMaximo = leerDouble(sc);

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

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
            // Fin del fichero
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

            opcionReserva = leerInt(sc);

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
        int idReserva = leerInt(sc);

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese ID.");
        }
    }

    public static void buscarReservaPorCliente(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del cliente: ");
        int idCliente = leerInt(sc);

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

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
            // Fin del fichero
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
        int idViaje = leerInt(sc);

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva para ese viaje.");
        }
    }

    public static void buscarReservaPorEstado(Scanner sc) throws IOException {

        String estado = leerTexto(sc, "el estado de la reserva: ");

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

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
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiReservas.close();

        if (!encontrado) {
            System.out.println("\nNo se ha encontrado ninguna reserva con ese estado.");
        }
    }

    public static void exportarXML(Scanner sc) throws IOException, ClassNotFoundException {

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

            opcionXML = leerInt(sc);

            switch (opcionXML) {

                case 1:
                    exportarClientesXML();
                    break;

                case 2:
                    exportarViajesXML();
                    break;

                case 3:
                    exportarDestinosXML();
                    break;

                case 4:
                    exportarHotelesXML();
                    break;

                case 5:
                    exportarReservasXML();
                    break;

                case 6:
                    exportarClientesXML();
                    exportarViajesXML();
                    exportarDestinosXML();
                    exportarHotelesXML();
                    exportarReservasXML();
                    break;

                case 7:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionXML != 7);
    }

    public static void exportarClientesXML() throws IOException, ClassNotFoundException {

        File ficheroClientes = new File("datos/dat/FicheroCliente.dat");

        if (!ficheroClientes.exists()) {
            System.out.println("\nNo existe el fichero de clientes.");
            return;
        }

        /* ----- Crear fichero '.xml' e insertarle los datos del fichero '.dat' ----- */

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
            FileOutputStream filexml = new FileOutputStream("datos/xml/Clientes.xml");
            xstream.toXML(listaClientes, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportarViajesXML() throws IOException, ClassNotFoundException {

        File ficheroViajes = new File("datos/dat/FicheroViaje.dat");

        if (!ficheroViajes.exists()) {
            System.out.println("\nNo existe el fichero de viajes.");
            return;
        }

        FileInputStream fiViajes = new FileInputStream(ficheroViajes);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        ListaViajes listaViajes = new ListaViajes();

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();
                listaViajes.anadir(viaje);
            }
        } catch (EOFException e) {
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de viajes.");
        }

        oiViajes.close();

        try {
            XStream xstream = new XStream();

            xstream.alias("ListaViajesTotales", ListaViajes.class);
            xstream.alias("DatosViaje", Viaje.class);
            xstream.addImplicitCollection(ListaViajes.class, "lista");

            FileOutputStream filexml = new FileOutputStream("datos/xml/Viajes.xml");
            xstream.toXML(listaViajes, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportarDestinosXML() throws IOException, ClassNotFoundException {

        File ficheroDestinos = new File("datos/dat/FicheroDestino.dat");

        if (!ficheroDestinos.exists()) {
            System.out.println("\nNo existe el fichero de destinos.");
            return;
        }

        FileInputStream fiDestinos = new FileInputStream(ficheroDestinos);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        ListaDestinos listaDestinos = new ListaDestinos();

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();
                listaDestinos.anadir(destino);
            }
        } catch (EOFException e) {
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de destinos.");
        }

        oiDestinos.close();

        try {
            XStream xstream = new XStream();

            xstream.alias("ListaDestinosTotales", ListaDestinos.class);
            xstream.alias("DatosDestino", Destino.class);
            xstream.addImplicitCollection(ListaDestinos.class, "lista");

            FileOutputStream filexml = new FileOutputStream("datos/xml/Destinos.xml");
            xstream.toXML(listaDestinos, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportarHotelesXML() throws IOException, ClassNotFoundException {

        File ficheroHoteles = new File("datos/dat/FicheroHotel.dat");

        if (!ficheroHoteles.exists()) {
            System.out.println("\nNo existe el fichero de hoteles.");
            return;
        }

        FileInputStream fiHoteles = new FileInputStream(ficheroHoteles);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        ListaHoteles listaHoteles = new ListaHoteles();

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();
                listaHoteles.anadir(hotel);
            }
        } catch (EOFException e) {
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de hoteles.");
        }

        oiHoteles.close();

        try {
            XStream xstream = new XStream();

            xstream.alias("ListaHotelesTotales", ListaHoteles.class);
            xstream.alias("DatosHotel", Hotel.class);
            xstream.addImplicitCollection(ListaHoteles.class, "lista");

            FileOutputStream filexml = new FileOutputStream("datos/xml/Hoteles.xml");
            xstream.toXML(listaHoteles, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportarReservasXML() throws IOException, ClassNotFoundException {

        File ficheroReservas = new File("datos/dat/FicheroReserva.dat");

        if (!ficheroReservas.exists()) {
            System.out.println("\nNo existe el fichero de reservas.");
            return;
        }

        FileInputStream fiReservas = new FileInputStream(ficheroReservas);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        ListaReservas listaReservas = new ListaReservas();

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();
                listaReservas.anadir(reserva);
            }
        } catch (EOFException e) {
            System.out.println("Todo ha ido bien. Se ha llegado al final del fichero de reservas.");
        }

        oiReservas.close();

        try {
            XStream xstream = new XStream();

            xstream.alias("ListaReservasTotales", ListaReservas.class);
            xstream.alias("DatosReserva", Reserva.class);
            xstream.addImplicitCollection(ListaReservas.class, "lista");

            FileOutputStream filexml = new FileOutputStream("datos/xml/Reservas.xml");
            xstream.toXML(listaReservas, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ----- Funciones auxiliares ----- */

    public static int leerInt(Scanner sc) {

        while (!sc.hasNextInt()) {
            System.out.println("Error: debes introducir un número.");
            sc.nextLine();
        }

        int numero = sc.nextInt();
        sc.nextLine();

        return numero;
    }

    public static double leerDouble(Scanner sc) {

        while (!sc.hasNextDouble()) {
            System.out.println("Error: debes introducir un número.");
            sc.nextLine();
        }

        double numero = sc.nextDouble();
        sc.nextLine();

        return numero;
    }

    // Se le pasarán como parámetros el "Scanner" y un texto opcional para completar la frase
    public static Date leerFecha(Scanner sc, String texto) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);

        while (true) {

            System.out.print("Teclea la fecha" + texto + " (dd/MM/yyyy): ");
            String fechaTexto = sc.nextLine();

            try {
                return formatoFecha.parse(fechaTexto);

            } catch (ParseException e) {
                System.out.println("\nFormato de fecha incorrecto. Utiliza dd/MM/yyyy.");
            }
        }
    }

    // Con esta función se verifica que se haya introducido algún texto por teclado
    public static String leerTexto(Scanner sc, String mensaje) {

        String texto;

        do {
            System.out.print("Teclea " + mensaje + ": ");
            texto = sc.nextLine();

            if (texto.trim().isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            }

        } while (texto.trim().isEmpty());

        return texto;
    }

    public static String leerEstadoReserva(Scanner sc) {

        while (true) {
            System.out.println("1. Pendiente");
            System.out.println("2. Confirmada");
            System.out.println("3. Cancelada");

            System.out.print("Teclea el número del estado de la reserva (1-3): ");
            String estado = sc.nextLine();

            switch (estado.toLowerCase()) {
                case "1":
                    return "Pendiente";

                case "2":
                    return "Confirmada";

                case "3":
                    return "Cancelada";

                default:
                    System.out.println("Estado no válido. Elige '1. Pendiente', '2. Confirmada' o '3. Cancelada'.");
            }
        }
    }

    public static String leerTipoViaje(Scanner sc) {

        while (true) {
            System.out.println("1. Escapada");
            System.out.println("2. Excursión de un día");
            System.out.println("3. Naturaleza");
            System.out.println("4. Playa");
            System.out.println("5. Vacaciones");
            System.out.println("6. Cultural");
            System.out.println("7. Internacional");

            System.out.print("Teclea el número del tipo de viaje (1-7): ");
            String tipo = sc.nextLine().trim();

            switch (tipo.toLowerCase()) {
                case "1":
                    return "Escapada";

                case "2":
                    return "Excursión de un día";

                case "3":
                    return "Naturaleza";

                case "4":
                    return "Playa";

                case "5":
                    return "Vacaciones";

                case "6":
                    return "Cultural";

                case "7":
                    return "Internacional";

                default:
                    System.out.println("Tipo de viaje no válido.");
            }
        }
    }

    public static String leerTipoDestino(Scanner sc) {

        while (true) {
            System.out.println("1. Costa");
            System.out.println("2. Playa");
            System.out.println("3. Urbano");
            System.out.println("4. Naturaleza");
            System.out.println("5. Cultural");

            System.out.print("Teclea el número del tipo de destino (1-5): ");
            String tipo = sc.nextLine().trim();

            switch (tipo.toLowerCase()) {
                case "1":
                    return "Costa";

                case "2":
                    return "Playa";

                case "3":
                    return "Urbano";

                case "4":
                    return "Naturaleza";

                case "5":
                    return "Cultural";

                default:
                    System.out.println("Tipo de destino no válido.");
            }
        }
    }

    public static int leerEstrellas(Scanner sc) {

        while (true) {
            int estrellas = leerInt(sc);

            if (estrellas >= 1 && estrellas <= 5) {
                return estrellas;
            }

            System.out.println("Las estrellas deben estar entre 1 y 5.");
        }
    }

    public static String leerTextoModificar(Scanner sc, String mensaje, String valorActual) {

        System.out.print("Teclea " + mensaje + ": " + valorActual);
        String texto = sc.nextLine();

        if (texto.isEmpty()) {
            return valorActual;
        }

        return texto;
    }

    public static String leerDni(Scanner sc) {

        while (true) {

            System.out.print("Teclea el DNI: ");
            // Obtener el DNI escrito por teclado y convertir sus letras en mayúsculas
            String dni = sc.nextLine().toUpperCase();

            // Debe ser equivalente a 8 caracteres numéricos y 1 letra
            if (dni.matches("\\d{8}[A-Z]")) {
                return dni;
            }

            System.out.println("DNI incorrecto. Debe tener 8 números y una letra.");
        }
    }

    public static String leerEmail(Scanner sc) {

        while (true) {

            System.out.print("Teclea el email: ");
            String email = sc.nextLine();

            // Debe ser equivalente a una cantidad mayor que 1 de caracteres alfanuméricos, un "@" exactamente, y otra serie de caracteres alfanuméricos
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.println("Email incorrecto.");
        }
    }

    public static String leerTelefono(Scanner sc) {

        while (true) {

            System.out.print("Teclea el teléfono: ");
            String telefono = sc.nextLine();

            // Debe ser equivalente a 9 números
            if (telefono.matches("\\d{9}")) {
                return telefono;
            }

            System.out.println("El teléfono debe tener 9 números.");
        }
    }

    public static int obtenerUltimoIdCliente() throws IOException {

        int ultimoId = 0;

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            return ultimoId;
        }

        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null && cliente.getId() > ultimoId) {
                    ultimoId = cliente.getId();
                }
            }

        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los clientes.");
        }

        oiClientes.close();

        return ultimoId;
    }

    public static int obtenerUltimoIdViaje() throws IOException {

        int ultimoId = 0;

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            return ultimoId;
        }

        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null && viaje.getId() > ultimoId) {
                    ultimoId = viaje.getId();
                }
            }

        } catch (EOFException e) {
            // Fin del fichero.
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los viajes.");
        }

        oiViajes.close();

        return ultimoId;
    }

    public static int obtenerUltimoIdDestino() throws IOException {

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            return 0;
        }

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        int ultimoId = 0;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getId() > ultimoId) {
                    ultimoId = destino.getId();
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los destinos.");
        }

        oiDestinos.close();

        return ultimoId;
    }

    public static int obtenerUltimoIdHotel() throws IOException {

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            return 0;
        }

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        int ultimoId = 0;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null && hotel.getId() > ultimoId) {
                    ultimoId = hotel.getId();
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los hoteles.");
        }

        oiHoteles.close();

        return ultimoId;
    }

    public static int obtenerUltimoIdReserva() throws IOException {

        File fichero = new File("datos/dat/FicheroReserva.dat");

        if (!fichero.exists()) {
            return 0;
        }

        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        int ultimoId = 0;

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null && reserva.getId() > ultimoId) {
                    ultimoId = reserva.getId();
                }
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer las reservas.");
        }

        oiReservas.close();

        return ultimoId;
    }

    public static boolean existeCliente(int idCliente) throws IOException {

        File fichero = new File("datos/dat/FicheroCliente.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream fiClientes = new FileInputStream(fichero);
        ObjectInputStream oiClientes = new ObjectInputStream(fiClientes);

        try {
            while (true) {
                Cliente cliente = (Cliente) oiClientes.readObject();

                if (cliente != null && cliente.getId() == idCliente) {
                    oiClientes.close();
                    return true;
                }
            }

        } catch (EOFException e) {
            // No se ha encontrado.
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los clientes.");
        }

        oiClientes.close();

        return false;
    }

    public static boolean existeViaje(int idViaje) throws IOException {

        File fichero = new File("datos/dat/FicheroViaje.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null && viaje.getId() == idViaje) {
                    oiViajes.close();
                    return true;
                }
            }

        } catch (EOFException e) {
            // No se ha encontrado.
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los viajes.");
        }

        oiViajes.close();

        return false;
    }

    public static boolean existeDestino(int idDestino) throws IOException {

        File fichero = new File("datos/dat/FicheroDestino.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getId() == idDestino) {
                    oiDestinos.close();
                    return true;
                }
            }

        } catch (EOFException e) {
            // No se ha encontrado.
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los destinos.");
        }

        oiDestinos.close();

        return false;
    }

    public static boolean existeHotel(int idHotel) throws IOException {

        File fichero = new File("datos/dat/FicheroHotel.dat");

        if (!fichero.exists()) {
            return false;
        }

        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null && hotel.getId() == idHotel) {
                    oiHoteles.close();
                    return true;
                }
            }

        } catch (EOFException e) {
            // No se ha encontrado.
        } catch (ClassNotFoundException e) {
            System.out.println("\nError al leer los hoteles.");
        }

        oiHoteles.close();

        return false;
    }
}