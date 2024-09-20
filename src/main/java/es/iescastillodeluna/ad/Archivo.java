package es.iescastillodeluna.ad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.stream.StreamSupport;

public class Archivo {

    /**
     * Ruta en la que se encuentra el archivo.
     */
    private Path ruta;

    /**
     * Fecha de modificación del archivo.
     */
    private FileTime fecha;

    /**
     * Propietario del archivo.
     */
    private String propietario;

    /**
     * Tamaño del archivo
     */
    private Tamanno tamanno;

    private Integer profundidad;

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

    public FileTime getFecha() {
        if(fecha == null) {
            try {
                fecha = (FileTime) Files.getAttribute(ruta, "lastModifiedTime");
            }
            catch(IOException err) {
                fecha = FileTime.fromMillis(0L);
            }
        }

        return fecha;
    }

    public Tamanno getTamanno() {
        if(tamanno == null) {
            try {
                tamanno = new Tamanno(Files.size(ruta));
            }
            catch(IOException err) {
                tamanno = new Tamanno(-1L);
            }
        }
        return tamanno.equals(new Tamanno(-1L))?null:tamanno;
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

    private Integer getProfundidad() {
        if(profundidad == null) {
            profundidad = (int) StreamSupport.stream(ruta.spliterator(), false).count();
        }
        return profundidad;
    }

    @Override
    public String toString() {
        return (getProfundidad() < 2 )?ruta.toString():String.format("[%s]%s", ruta.getParent(), nombre());
    }
}
