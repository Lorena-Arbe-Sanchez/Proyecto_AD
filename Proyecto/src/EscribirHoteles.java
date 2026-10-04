import com.thoughtworks.xstream.XStream;

import java.io.*;

public class EscribirHoteles {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File ficheroHoteles = new File("FicheroHotel.dat");

        FileOutputStream foHoteles = new FileOutputStream(ficheroHoteles);
        ObjectOutputStream ooHoteles = new ObjectOutputStream(foHoteles);

        // TODO : Quitar algún hotel para que algunos destinos tengan 0, otros 1, y otros varios
        Hotel hotel1 = new Hotel(1, "Hotel Jauregui", 4, "Zuloaga Kalea, 5, Hondarribia", 145.00, "WiFi, restaurante, desayuno, parking");
        Hotel hotel2 = new Hotel(2, "Hotel Palacio Obispo", 4, "Apezpiku Kalea, 1, Hondarribia", 160.00, "WiFi, restaurante, desayuno");
        Hotel hotel3 = new Hotel(3, "Hotel Onyarbi", 2, "Axular Kalea, 1, Hondarribia", 95.00, "WiFi, parking");
        Hotel hotel4 = new Hotel(4, "Hotel Arbaso", 4, "Hondarribia Kalea, 24, Donostia", 190.00, "WiFi, restaurante, gimnasio, bicicletas");
        Hotel hotel5 = new Hotel(5, "Hotel Ezeiza", 2, "Avenida Satrustegi, 13, Donostia", 105.00, "WiFi, restaurante, parking");
        Hotel hotel6 = new Hotel(6, "Hotel Molina Lario", 4, "Molina Lario, 20, Málaga", 175.00, "WiFi, piscina, restaurante, terraza");
        Hotel hotel7 = new Hotel(7, "Hotel Soho Boutique Las Vegas", 3, "Paseo de Sancha, 22, Málaga", 110.00, "WiFi, piscina, desayuno, terraza");
        Hotel hotel8 = new Hotel(8, "Hotel Regina", 4, "Alcalá, 19, Madrid", 180.00, "WiFi, restaurante, desayuno");
        Hotel hotel9 = new Hotel(9, "Hotel Mediodía", 2, "Plaza del Emperador Carlos V, 8, Madrid", 90.00, "WiFi, desayuno, recepción 24 horas");
        Hotel hotel10 = new Hotel(10, "Hotel Ibaia", 3, "76 Avenue des Mimosas, Hendaya", 125.00, "WiFi, piscina, spa, parking");
        Hotel hotel11 = new Hotel(11, "Hotel Valencia", 2, "29 Boulevard Général Leclerc, Hendaya", 95.00, "WiFi, desayuno, terraza");
        Hotel hotel12 = new Hotel(12, "Hotel Kanala", 1, "Deba, Gipuzkoa", 75.00, "WiFi, restaurante, parking");
        Hotel hotel13 = new Hotel(13, "Hotel Zarauz", 3, "Nafarroa Kalea, Zarautz", 100.00, "WiFi, restaurante, desayuno");
        Hotel hotel14 = new Hotel(14, "Hotel Norte", 1, "Amezti Kalea, Zarautz", 70.00, "WiFi, desayuno");
        Hotel hotel15 = new Hotel(15, "Hotel California Palace", 4, "Carrer de la Ciutat de Reus, Salou", 120.00, "WiFi, piscina, restaurante, gimnasio");
        Hotel hotel16 = new Hotel(16, "Hotel Jaime I", 3, "Carrer de Logronyo, Salou", 105.00, "WiFi, piscina, restaurante");
        Hotel hotel17 = new Hotel(17, "ibis Paris Tour Eiffel Cambronne", 3, "2 Rue Cambronne, París", 160.00, "WiFi, restaurante, bar, recepción 24 horas");
        Hotel hotel18 = new Hotel(18, "Hotel Gracery Shinjuku", 4, "Kabukicho, Shinjuku, Tokio", 180.00, "WiFi, restaurante, recepción 24 horas");
        Hotel hotel19 = new Hotel(19, "APA Hotel Shinjuku Gyoenmae", 3, "Shinjuku, Tokio", 110.00, "WiFi, restaurante, baño público");

        ooHoteles.writeObject(hotel1);
        ooHoteles.writeObject(hotel2);
        ooHoteles.writeObject(hotel3);
        ooHoteles.writeObject(hotel4);
        ooHoteles.writeObject(hotel5);
        ooHoteles.writeObject(hotel6);
        ooHoteles.writeObject(hotel7);
        ooHoteles.writeObject(hotel8);
        ooHoteles.writeObject(hotel9);
        ooHoteles.writeObject(hotel10);
        ooHoteles.writeObject(hotel11);
        ooHoteles.writeObject(hotel12);
        ooHoteles.writeObject(hotel13);
        ooHoteles.writeObject(hotel14);
        ooHoteles.writeObject(hotel15);
        ooHoteles.writeObject(hotel16);
        ooHoteles.writeObject(hotel17);
        ooHoteles.writeObject(hotel18);
        ooHoteles.writeObject(hotel19);

        ooHoteles.close();
    }
}