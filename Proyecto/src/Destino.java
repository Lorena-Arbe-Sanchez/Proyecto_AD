import java.io.Serializable;

public class Destino implements Serializable {

    private int id;
    private String ciudad;
    private String pais;
    private String descripcion;
    private String tipoDestino;
    private String idioma;
    private String moneda;
    private String imagenUrl;

    public Destino(int id, String ciudad, String pais, String descripcion, String tipoDestino, String idioma,
                   String moneda, String imagenUrl) {
        this.id = id;
        this.ciudad = ciudad;
        this.pais = pais;
        this.descripcion = descripcion;
        this.tipoDestino = tipoDestino;
        this.idioma = idioma;
        this.moneda = moneda;
        this.imagenUrl = imagenUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(String tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void mostrarTodosDatos() {
        System.out.println("\nID: " + id +
                "\nCiudad: " + ciudad +
                "\nPaís: " + pais +
                "\nDescripción: " + descripcion +
                "\nTipo de destino: " + tipoDestino +
                "\nIdioma: " + idioma +
                "\nMoneda: " + moneda +
                "\nURL de la imagen: " + imagenUrl);
    }
}
