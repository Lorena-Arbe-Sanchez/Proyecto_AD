import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EscribirReservas {
    public static void main(String[] args) throws IOException, ParseException {

        File ficheroReservas = new File("datos/dat/FicheroReserva.dat");

        FileOutputStream foReservas = new FileOutputStream(ficheroReservas);
        ObjectOutputStream ooReservas = new ObjectOutputStream(foReservas);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Date fechaReserva1 = formatoFecha.parse("01/10/2026");
        Date fechaReserva2 = formatoFecha.parse("05/10/2026");
        Date fechaReserva3 = formatoFecha.parse("10/10/2026");
        Date fechaReserva4 = formatoFecha.parse("15/10/2026");
        Date fechaReserva5 = formatoFecha.parse("20/10/2026");
        Date fechaReserva6 = formatoFecha.parse("25/10/2026");
        Date fechaReserva7 = formatoFecha.parse("01/11/2026");
        Date fechaReserva8 = formatoFecha.parse("05/11/2026");
        Date fechaReserva9 = formatoFecha.parse("20/11/2026");
        Date fechaReserva10 = formatoFecha.parse("01/12/2026");

        Reserva reserva1 = new Reserva(1, 1, 1, fechaReserva1, 2, 500.00, "Confirmada");
        Reserva reserva2 = new Reserva(2, 2, 2, fechaReserva2, 3, 135.00, "Confirmada");
        Reserva reserva3 = new Reserva(3, 3, 3, fechaReserva3, 1, 320.00, "Confirmada");
        Reserva reserva4 = new Reserva(4, 4, 4, fechaReserva4, 2, 700.00, "Confirmada");
        Reserva reserva5 = new Reserva(5, 5, 5, fechaReserva5, 2, 110.00, "Confirmada");
        Reserva reserva6 = new Reserva(6, 6, 6, fechaReserva6, 1, 140.00, "Confirmada");
        Reserva reserva7 = new Reserva(7, 7, 7, fechaReserva7, 4, 720.00, "Confirmada");
        Reserva reserva8 = new Reserva(8, 8, 8, fechaReserva8, 2, 560.00, "Pendiente");
        Reserva reserva9 = new Reserva(9, 9, 9, fechaReserva9, 1, 650.00, "Confirmada");
        Reserva reserva10 = new Reserva(10, 10, 10, fechaReserva10, 2, 2400.00, "Pendiente");

        ooReservas.writeObject(reserva1);
        ooReservas.writeObject(reserva2);
        ooReservas.writeObject(reserva3);
        ooReservas.writeObject(reserva4);
        ooReservas.writeObject(reserva5);
        ooReservas.writeObject(reserva6);
        ooReservas.writeObject(reserva7);
        ooReservas.writeObject(reserva8);
        ooReservas.writeObject(reserva9);
        ooReservas.writeObject(reserva10);

        ooReservas.close();
    }
}