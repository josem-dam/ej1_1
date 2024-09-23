package com.ad.ej1_1.lista.archivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Modela los tipos de archivo.
 */
public enum TipoArchivo {
    /**
     * Archivo regular
     */
    REGULAR(Files::isRegularFile),
    /**
     * Directorio.
     */
    DIRECTORIO(Files::isDirectory),
    /**
     * Enlace simbólico.
     */
    SIMBOLICO(Files::isSymbolicLink),
    /**
     * Archivo oculto.
     */
    OCULTO(TipoArchivo::isHidden);

    /**
     * El método que comprueba si el archivo es del tipo.
     */
    private Predicate<Path> check;

    /**
     * Wrapper para Files.isHidden que evita al excepción.
     * Si se produce, se devuelve verdadero.
     * @param ruta -  El archivo que se quiere comprobar.
     * @return true, si el archivo es oculto.
     */
    private static boolean isHidden(Path ruta) {
        try { return Files.isHidden(ruta); }
        catch(IOException err) { return true; }        
    }

    TipoArchivo(Predicate<Path> check) {
        this.check = check;
    }

    /**
     * Determina si el archivo proporcionado es del tipo.
     * @param ruta - El archivo a comprobar.
     * @return true, si es el tipo.
     */
    public boolean es(Path ruta) {
        return check.test(ruta);
    }
}