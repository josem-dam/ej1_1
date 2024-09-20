package es.iescastillodeluna.ad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Archivo {

    /**
     * Ruta en la que se encuentra el archivo.
     */
    private Path ruta;

    /**
     * Fecha de modificación del archivo.
     */
    private Date fecha;

    /**
     * Propietario del archivo.
     */
    private String propietario;

    /**
     * Tamaño del archivo
     */
    private Long tamanno;

    /**
     * Constructor de Archivo
     * @param ruta - Ruta del archivo
     */
    public Archivo(Path ruta) {
        assert Files.exists(ruta): String.format("%s: NO EXISTE");
        this.ruta = ruta;

    }

    /**
     * Constructor de Archivo
     * @param ruta - Ruta del archivo
     */
    public Archivo(String ruta) {
        this(Path.of(ruta));
    }

    /**
     * Devuelve el nombre del archivo
     * @return
     */
    public String nombre() {
        return ruta.getFileName().toString();
    }

    public Date getFecha() {
        if(fecha == null) {
            try {
                fecha = (Date) Files.getAttribute(ruta, "lastModifiedTime");
            }
            catch(IOException err) {
                fecha = new Date(0);
            }
        }

        return fecha;
    }

    public Long getTamanno() {
        if(tamanno == null) {
            try {
                tamanno = Files.size(ruta);
            }
            catch(IOException err) {
                tamanno = -1L;
            }
        }
        return tamanno == -1?null:tamanno;
    }

    public String getPropietario() {
        if(propietario == null) {
            try {
                propietario = Files.getOwner(ruta).getName();
            }
            catch(IOException err) {
                propietario = "";
            }
        }

        return propietario == ""?null:propietario;
    }
}
