import com.thoughtworks.xstream.XStream;

import java.io.*;

public class EscribirDestinos {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File ficheroDestinos = new File("FicheroDestino.dat");

        FileOutputStream foDestinos = new FileOutputStream(ficheroDestinos);
        ObjectOutputStream ooDestinos = new ObjectOutputStream(foDestinos);

        Destino destino1 = new Destino(1, "Hondarribi", "España", "Ciudad costera de Gipuzkoa conocida por su casco histórico, puerto y playas.", "Costa", "Español", "Euro", "https://drive.google.com/file/d/1-LZFONW7yx8Wd3luLYvK9Vp-0MErubqK/view?usp=drive_link");
        Destino destino2 = new Destino(2, "Donosti", "España", "Ciudad costera conocida por la playa de La Concha, su gastronomía y su casco antiguo.", "Costa", "Español", "Euro", "https://drive.google.com/file/d/1CIJsXi46hwn2KY1qSayibvLEV0zL2uX9/view?usp=drive_link");
        Destino destino3 = new Destino(3, "Málaga", "España", "Ciudad andaluza con playas, patrimonio histórico, museos y una amplia oferta cultural.", "Playa", "Español", "Euro", "https://drive.google.com/file/d/1SkWqIgIPyzy7q3SPmJ8O10Tr4lWYkZlZ/view?usp=drive_link");
        Destino destino4 = new Destino(4, "Madrid", "España", "Capital de España con numerosos museos, monumentos, parques y zonas comerciales.", "Urbano", "Español", "Euro", "https://drive.google.com/file/d/1WYArYOZpqQn7Uozu3EZuOOvfAWR1qUAn/view?usp=drive_link");
        Destino destino5 = new Destino(5, "Hendaya", "Francia", "Localidad costera francesa situada junto a la frontera con España y conocida por su playa.", "Playa", "Francés", "Euro", "https://drive.google.com/file/d/1fGNK-IvF-6rBQ_KnfV4v8wKW3H3UW1r-/view?usp=drive_link");
        Destino destino6 = new Destino(6, "Deba", "España", "Localidad costera de Gipuzkoa rodeada de naturaleza, playas y acantilados.", "Naturaleza", "Español", "Euro", "https://drive.google.com/file/d/1aOmTC7663u85iFrHb4okbxewsRlvjwaJ/view?usp=drive_link");
        Destino destino7 = new Destino(7, "Zarautz", "España", "Localidad costera de Gipuzkoa conocida por su playa, surf y ambiente turístico.", "Playa", "Español", "Euro", "https://drive.google.com/file/d/1QamN01XCTCeGtA4RSQSOhZFaiIemR7W4/view?usp=drive_link");
        Destino destino8 = new Destino(8, "Salou", "España", "Destino turístico de la Costa Dorada conocido por sus playas y su oferta de ocio.", "Playa", "Español", "Euro", "https://drive.google.com/file/d/1L6zgsp9c4h4_w_sPb6GcJ5ayCC3EpvFr/view?usp=drive_link");
        Destino destino9 = new Destino(9, "París", "Francia", "Capital francesa conocida por sus monumentos, museos, arquitectura y gastronomía.", "Cultural", "Francés", "Euro", "https://drive.google.com/file/d/1Nl2IzvhtmZ-FzkFhGnkalUmTsp7-idNo/view?usp=drive_link");
        Destino destino10 = new Destino(10, "Tokio", "Japón", "Gran ciudad japonesa que combina tradición, cultura, tecnología y zonas urbanas.", "Urbano", "Japonés", "Yen", "https://drive.google.com/file/d/195v-eDbc_I4ahUWkvyBEpz4jefvo6tDJ/view?usp=drive_link");

        ooDestinos.writeObject(destino1);
        ooDestinos.writeObject(destino2);
        ooDestinos.writeObject(destino3);
        ooDestinos.writeObject(destino4);
        ooDestinos.writeObject(destino5);
        ooDestinos.writeObject(destino6);
        ooDestinos.writeObject(destino7);
        ooDestinos.writeObject(destino8);
        ooDestinos.writeObject(destino9);
        ooDestinos.writeObject(destino10);

        ooDestinos.close();

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

            FileOutputStream filexml = new FileOutputStream("Destinos.xml");
            xstream.toXML(listaDestinos, filexml);
            filexml.close();

            System.out.println("Fichero XML creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}