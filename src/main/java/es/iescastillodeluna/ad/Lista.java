package es.iescastillodeluna.ad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import es.iescastillodeluna.ad.archivo.Archivo;

/**
 * Lista contenidos de directorios.
 */
public class Lista {

    private Lista() {}

    /**
     * Comprueba si no es un archivo oculto o un enlace simbólico.
     * @param ruta - La ruta del archivo.
     * @return true, si no es oculto ni un enlace.
     */
    private static boolean descartaOcultoYEnlace(Path ruta) {
        try {
            return !Files.isHidden(ruta) && !Files.isSymbolicLink(ruta);
        }
        catch(IOException err) {
            return false;
        }
    }

    /**
     * Devuelve los archivos que son estrictamente contenidos por un directorio excepto ocultos
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param limite - Límite máximo de archivos que se quieren devolver.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException
     */
    public static Stream<Archivo> listar(Path dir, long limite) throws IOException {
        if(!Files.isDirectory(dir)) throw new RuntimeException("El argumento debe ser un directorio");

        return Files.list(dir).filter(Lista::descartaOcultoYEnlace).map(f -> new Archivo(f)).limit(limite);
    } 

    public static Stream<Archivo> listar(Path dir)  throws IOException {
        return listar(dir, Long.MAX_VALUE);
    }

    public static Stream<Archivo> recorrer(Path dir, long limite, int prof) throws IOException {
        if(!Files.isDirectory(dir)) throw new RuntimeException("El argumento debe ser un directorio");

        return Files.walk(dir, prof).filter(Lista::descartaOcultoYEnlace).map(f -> new Archivo(f)).limit(limite);
    }

    public static Stream<Archivo> recorrer(Path dir, long limite) throws IOException {
        return recorrer(dir, limite, Integer.MAX_VALUE);
    }

    public static Stream<Archivo> recorrer(Path dir, int prof) throws IOException {
        return recorrer(dir, Long.MAX_VALUE, prof);
    }

    public static Stream<Archivo> recorrer(Path dir) throws IOException {
        return recorrer(dir, Long.MAX_VALUE, Integer.MAX_VALUE);
    }
}
