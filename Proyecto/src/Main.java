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

    // TODO : En las funciones de "mostrar" -->  Poner un recuento de la cantidad de datos que hay como primera línea y luego ya lo demás
    public static void mostrarClientes() throws IOException {

        File fichero = new File("datos/dat/FicheroCliente.dat");
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

    public static void anadirCliente(Scanner sc) throws IOException {

        // TODO : Ponerlo automático en todos (que lo ponga como +1 del último q haya en registros)
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

        File fichero = new File("datos/dat/FicheroCliente.dat");

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

    // TODO : Mirar si en los apuntes se hacía así (y los demás a ver si aparecen tambn)
    // TODO : A la hora de poner los datos a modificar --> Hacer que aparezcan los datos para poder cambiarles poco (que aparezcan ya al teclear "3")
    public static void modificarCliente(Scanner sc) throws IOException {

        // TODO : Mejor por DNI
        System.out.print("\nTeclea el ID del cliente que quieres modificar: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroCliente.dat");

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

    public static void eliminarCliente(Scanner sc) throws IOException {

        // TODO : Mejor por DNI
        System.out.print("\nTeclea el ID del cliente que quieres eliminar: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroCliente.dat");

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

            opcionViajes = sc.nextInt();
            sc.nextLine();

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
        FileInputStream fiViajes = new FileInputStream(fichero);
        ObjectInputStream oiViajes = new ObjectInputStream(fiViajes);

        try {
            while (true) {
                Viaje viaje = (Viaje) oiViajes.readObject();

                if (viaje != null) {
                    viaje.mostrarTodosDatos();
                }
            }
        } catch (EOFException e) {
            // Se ha llegado al final del fichero
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        oiViajes.close();
    }

    public static void anadirViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el ID del destino: ");
        int idDestino = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el ID del hotel (0 si no hay hotel): ");
        int idHotelIntroducido = sc.nextInt();
        sc.nextLine();

        Integer idHotel = null;

        if (idHotelIntroducido != 0) {
            idHotel = idHotelIntroducido;
        }

        System.out.print("Teclea el origen: ");
        String origen = sc.nextLine();

        System.out.print("Teclea la fecha de salida (dd/MM/yyyy): ");
        String fechaSalidaTexto = sc.nextLine();

        System.out.print("Teclea la fecha de regreso (dd/MM/yyyy): ");
        String fechaRegresoTexto = sc.nextLine();

        System.out.print("Teclea el precio: ");
        double precio = sc.nextDouble();

        System.out.print("Teclea el número de plazas totales: ");
        int plazasTotales = sc.nextInt();

        System.out.print("Teclea el número de plazas disponibles: ");
        int plazasDisponibles = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el tipo de viaje: ");
        String tipo = sc.nextLine();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fechaSalida;
        Date fechaRegreso;

        try {
            fechaSalida = formatoFecha.parse(fechaSalidaTexto);
            fechaRegreso = formatoFecha.parse(fechaRegresoTexto);
        } catch (ParseException e) {
            // TODO : Que esto lo saque antes de llegar al final de las preguntas y que se pueda repetir (bucle)
            System.out.println("\nFormato de fecha incorrecto.");
            return;
        }

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
                tipo
        );

        File fichero = new File("datos/dat/FicheroViaje.dat");

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
                // Se ha llegado al final del fichero
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
    }

    public static void modificarViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje que quieres modificar: ");
        int idViaje = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroViaje.dat");

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
                        viaje.setIdDestino(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Nuevo ID del hotel (0 si no hay hotel): ");
                        int idHotelIntroducido = sc.nextInt();
                        sc.nextLine();

                        if (idHotelIntroducido == 0) {
                            viaje.setIdHotel(null);
                        } else {
                            viaje.setIdHotel(idHotelIntroducido);
                        }

                        System.out.print("Nuevo origen: ");
                        viaje.setOrigen(sc.nextLine());

                        System.out.print("Nueva fecha de salida (dd/MM/yyyy): ");
                        String fechaSalidaTexto = sc.nextLine();

                        System.out.print("Nueva fecha de regreso (dd/MM/yyyy): ");
                        String fechaRegresoTexto = sc.nextLine();

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            viaje.setFechaSalida(formatoFecha.parse(fechaSalidaTexto));
                            viaje.setFechaRegreso(formatoFecha.parse(fechaRegresoTexto));
                        } catch (ParseException e) {
                            System.out.println("\nFormato de fecha incorrecto.");
                            oiViajes.close();
                            return;
                        }

                        System.out.print("Nuevo precio: ");
                        viaje.setPrecio(sc.nextDouble());

                        System.out.print("Nuevo número de plazas totales: ");
                        viaje.setPlazasTotales(sc.nextInt());

                        System.out.print("Nuevo número de plazas disponibles: ");
                        viaje.setPlazasDisponibles(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Nuevo tipo de viaje: ");
                        viaje.setTipoViaje(sc.nextLine());

                        encontrado = true;
                    }

                    viajes.add(viaje);
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

            System.out.println("\nViaje modificado correctamente.");

        } else {
            System.out.println("\nNo se ha encontrado ningún viaje con ese ID.");
        }
    }

    public static void eliminarViaje(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID del viaje que quieres eliminar: ");
        int idViaje = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroViaje.dat");

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

            opcionDestinos = sc.nextInt();
            sc.nextLine();

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
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {
                    destino.mostrarTodosDatos();
                }
            }
        } catch (EOFException e) {
            System.out.println("\nFin del fichero.");
        }

        oiDestinos.close();
    }

    public static void anadirDestino(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea la ciudad: ");
        String ciudad = sc.nextLine();

        System.out.print("Teclea el país: ");
        String pais = sc.nextLine();

        System.out.print("Teclea la descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Teclea el tipo de destino: ");
        String tipoDestino = sc.nextLine();

        System.out.print("Teclea el idioma: ");
        String idioma = sc.nextLine();

        System.out.print("Teclea la moneda: ");
        String moneda = sc.nextLine();

        System.out.print("Teclea la URL de la imagen: ");
        String imagenUrl = sc.nextLine();

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
    }

    public static void modificarDestino(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del destino que quieres modificar: ");
        int idDestino = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        ListaDestinos listaDestinos = new ListaDestinos();
        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null) {

                    if (destino.getId() == idDestino) {

                        System.out.print("Nueva ciudad: ");
                        destino.setCiudad(sc.nextLine());

                        System.out.print("Nuevo país: ");
                        destino.setPais(sc.nextLine());

                        System.out.print("Nueva descripción: ");
                        destino.setDescripcion(sc.nextLine());

                        System.out.print("Nuevo tipo de destino: ");
                        destino.setTipoDestino(sc.nextLine());

                        System.out.print("Nuevo idioma: ");
                        destino.setIdioma(sc.nextLine());

                        System.out.print("Nueva moneda: ");
                        destino.setMoneda(sc.nextLine());

                        System.out.print("Nueva URL de imagen: ");
                        destino.setImagenUrl(sc.nextLine());

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
        int idDestino = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroDestino.dat");
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

            opcionHoteles = sc.nextInt();
            sc.nextLine();

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
        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {
                    hotel.mostrarTodosDatos();
                }
            }
        } catch (EOFException e) {
            System.out.println("\nFin del fichero.");
        }

        oiHoteles.close();
    }

    public static void anadirHotel(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Teclea el número de estrellas: ");
        int estrellas = sc.nextInt();
        sc.nextLine();

        System.out.print("Teclea la dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teclea el precio por noche: ");
        double precioNoche = sc.nextDouble();
        sc.nextLine();

        System.out.print("Teclea los servicios: ");
        String servicios = sc.nextLine();

        Hotel nuevoHotel = new Hotel(
                id,
                nombre,
                estrellas,
                direccion,
                precioNoche,
                servicios
        );

        File fichero = new File("datos/dat/FicheroHotel.dat");

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
            // Se ha llegado al final del fichero
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
    }

    public static void modificarHotel(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID del hotel que quieres modificar: ");
        int idHotel = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroHotel.dat");
        FileInputStream fiHoteles = new FileInputStream(fichero);
        ObjectInputStream oiHoteles = new ObjectInputStream(fiHoteles);

        ListaHoteles listaHoteles = new ListaHoteles();
        boolean encontrado = false;

        try {
            while (true) {
                Hotel hotel = (Hotel) oiHoteles.readObject();

                if (hotel != null) {

                    if (hotel.getId() == idHotel) {

                        System.out.print("Nuevo nombre: ");
                        hotel.setNombre(sc.nextLine());

                        System.out.print("Nuevo número de estrellas: ");
                        hotel.setEstrellas(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Nueva dirección: ");
                        hotel.setDireccion(sc.nextLine());

                        System.out.print("Nuevo precio por noche: ");
                        hotel.setPrecioNoche(sc.nextDouble());
                        sc.nextLine();

                        System.out.print("Nuevos servicios: ");
                        hotel.setServicios(sc.nextLine());

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
        int idHotel = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroHotel.dat");
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

            opcionReservas = sc.nextInt();
            sc.nextLine();

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
        FileInputStream fiReservas = new FileInputStream(fichero);
        ObjectInputStream oiReservas = new ObjectInputStream(fiReservas);

        try {
            while (true) {
                Reserva reserva = (Reserva) oiReservas.readObject();

                if (reserva != null) {
                    reserva.mostrarTodosDatos();
                }
            }
        } catch (EOFException e) {
            System.out.println("\nFin del fichero.");
        }

        oiReservas.close();
    }

    public static void anadirReserva(Scanner sc) throws IOException {

        System.out.print("\nTeclea el ID: ");
        int id = sc.nextInt();

        System.out.print("Teclea el ID del cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("Teclea el ID del viaje: ");
        int idViaje = sc.nextInt();

        sc.nextLine();

        System.out.print("Teclea la fecha de reserva (dd/MM/yyyy): ");
        String fechaTexto = sc.nextLine();

        Date fechaReserva;

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            fechaReserva = formatoFecha.parse(fechaTexto);
        } catch (ParseException e) {
            System.out.println("\nFormato de fecha no válido.");
            return;
        }

        System.out.print("Teclea el número de personas: ");
        int numeroPersonas = sc.nextInt();

        System.out.print("Teclea el precio total: ");
        double precioTotal = sc.nextDouble();

        sc.nextLine();

        // TODO : Control de errores --> Que solo se pueda escribir una de las opciones y no texto aleatorio
        System.out.print("Teclea el estado de la reserva: ");
        String estado = sc.nextLine();

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
            // Se ha llegado al final del fichero
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
    }

    public static void modificarReserva(Scanner sc) throws IOException, ClassNotFoundException {

        System.out.print("\nTeclea el ID de la reserva que quieres modificar: ");
        int idReserva = sc.nextInt();

        File fichero = new File("datos/dat/FicheroReserva.dat");
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
                        reserva.setIdCliente(sc.nextInt());

                        System.out.print("Nuevo ID del viaje: ");
                        reserva.setIdViaje(sc.nextInt());

                        sc.nextLine();

                        System.out.print("Nueva fecha de reserva (dd/MM/yyyy): ");
                        String fechaTexto = sc.nextLine();

                        try {
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                            reserva.setFechaReserva(formatoFecha.parse(fechaTexto));
                        } catch (ParseException e) {
                            System.out.println("\nFormato de fecha no válido.");
                        }

                        System.out.print("Nuevo número de personas: ");
                        reserva.setNumeroPersonas(sc.nextInt());

                        System.out.print("Nuevo precio total: ");
                        reserva.setPrecioTotal(sc.nextDouble());

                        sc.nextLine();

                        System.out.print("Nuevo estado: ");
                        reserva.setEstado(sc.nextLine());

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
        int idReserva = sc.nextInt();
        sc.nextLine();

        File fichero = new File("datos/dat/FicheroReserva.dat");
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

        File fichero = new File("datos/dat/FicheroCliente.dat");
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

        File fichero = new File("datos/dat/FicheroCliente.dat");
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

        File fichero = new File("datos/dat/FicheroCliente.dat");
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

        File fichero = new File("datos/dat/FicheroViaje.dat");
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

        File fichero = new File("datos/dat/FicheroDestino.dat");
        FileInputStream fiDestinos = new FileInputStream(fichero);
        ObjectInputStream oiDestinos = new ObjectInputStream(fiDestinos);

        boolean encontrado = false;

        try {
            while (true) {
                Destino destino = (Destino) oiDestinos.readObject();

                if (destino != null && destino.getCiudad().equalsIgnoreCase(ciudad)) {
                    int idDestino = destino.getId();

                    FileInputStream fiViajes = new FileInputStream("datos/dat/FicheroViaje.dat");
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

        File fichero = new File("datos/dat/FicheroViaje.dat");
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

        File fichero = new File("datos/dat/FicheroViaje.dat");
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

        File fichero = new File("datos/dat/FicheroDestino.dat");
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

        File fichero = new File("datos/dat/FicheroDestino.dat");
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

        File fichero = new File("datos/dat/FicheroDestino.dat");
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

        File fichero = new File("datos/dat/FicheroHotel.dat");
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

        File fichero = new File("datos/dat/FicheroHotel.dat");
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

        File fichero = new File("datos/dat/FicheroReserva.dat");
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

        File fichero = new File("datos/dat/FicheroReserva.dat");
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

        File fichero = new File("datos/dat/FicheroReserva.dat");
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

        File fichero = new File("datos/dat/FicheroReserva.dat");
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

            opcionXML = sc.nextInt();
            sc.nextLine();

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
}