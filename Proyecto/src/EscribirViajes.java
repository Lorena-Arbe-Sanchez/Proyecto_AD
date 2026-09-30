import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EscribirViajes {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        File ficheroViajes = new File("FicheroViaje.dat");

        FileOutputStream foViajes = new FileOutputStream(ficheroViajes);
        ObjectOutputStream ooViajes = new ObjectOutputStream(foViajes);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fechaSalida1 = formatoFecha.parse("10/10/2026");
        Date fechaRegreso1 = formatoFecha.parse("12/10/2026");
        Date fechaSalida2 = formatoFecha.parse("17/10/2026");
        Date fechaRegreso2 = formatoFecha.parse("17/10/2026");
        Date fechaSalida3 = formatoFecha.parse("24/10/2026");
        Date fechaRegreso3 = formatoFecha.parse("26/10/2026");
        Date fechaSalida4 = formatoFecha.parse("31/10/2026");
        Date fechaRegreso4 = formatoFecha.parse("02/11/2026");
        Date fechaSalida5 = formatoFecha.parse("07/11/2026");
        Date fechaRegreso5 = formatoFecha.parse("07/11/2026");
        Date fechaSalida6 = formatoFecha.parse("14/11/2026");
        Date fechaRegreso6 = formatoFecha.parse("15/11/2026");
        Date fechaSalida7 = formatoFecha.parse("21/11/2026");
        Date fechaRegreso7 = formatoFecha.parse("22/11/2026");
        Date fechaSalida8 = formatoFecha.parse("28/11/2026");
        Date fechaRegreso8 = formatoFecha.parse("30/11/2026");
        Date fechaSalida9 = formatoFecha.parse("05/12/2026");
        Date fechaRegreso9 = formatoFecha.parse("08/12/2026");
        Date fechaSalida10 = formatoFecha.parse("15/12/2026");
        Date fechaRegreso10 = formatoFecha.parse("22/12/2026");

        // TODO : Poner los datos correctos + Lo del "null" en hotel
        Viaje viaje1 = new Viaje(1, 1, 1, "Vitoria-Gasteiz", fechaSalida1, fechaRegreso1, 250.00, 20, 20, "Escapada");
        Viaje viaje2 = new Viaje(2, 2, null, "Vitoria-Gasteiz", fechaSalida2, fechaRegreso2, 45.00, 30, 30, "Excursión de un día");
        Viaje viaje3 = new Viaje(3, 3, 6, "Vitoria-Gasteiz", fechaSalida3, fechaRegreso3, 320.00, 25, 25, "Escapada");
        Viaje viaje4 = new Viaje(4, 4, 8, "Vitoria-Gasteiz", fechaSalida4, fechaRegreso4, 350.00, 30, 30, "Escapada");
        Viaje viaje5 = new Viaje(5, 5, null, "Vitoria-Gasteiz", fechaSalida5, fechaRegreso5, 55.00, 25, 25, "Excursión de un día");
        Viaje viaje6 = new Viaje(6, 6, 12, "Vitoria-Gasteiz", fechaSalida6, fechaRegreso6, 140.00, 20, 20, "Naturaleza");
        Viaje viaje7 = new Viaje(7, 7, 13, "Vitoria-Gasteiz", fechaSalida7, fechaRegreso7, 180.00, 25, 25, "Playa");
        Viaje viaje8 = new Viaje(8, 8, 15, "Vitoria-Gasteiz", fechaSalida8, fechaRegreso8, 280.00, 30, 30, "Vacaciones");
        Viaje viaje9 = new Viaje(9, 9, 17, "Vitoria-Gasteiz", fechaSalida9, fechaRegreso9, 650.00, 20, 20, "Cultural");
        Viaje viaje10 = new Viaje(10, 10, 18, "Vitoria-Gasteiz", fechaSalida10, fechaRegreso10, 1200.00, 20, 20, "Internacional");

        ooViajes.writeObject(viaje1);
        ooViajes.writeObject(viaje2);
        ooViajes.writeObject(viaje3);
        ooViajes.writeObject(viaje4);
        ooViajes.writeObject(viaje5);
        ooViajes.writeObject(viaje6);
        ooViajes.writeObject(viaje7);
        ooViajes.writeObject(viaje8);
        ooViajes.writeObject(viaje9);
        ooViajes.writeObject(viaje10);

        ooViajes.close();

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

            FileOutputStream filexml = new FileOutputStream("Viajes.xml");
            xstream.toXML(listaViajes, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}