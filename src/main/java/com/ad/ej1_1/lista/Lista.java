package com.ad.ej1_1.lista;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

import com.ad.ej1_1.lista.archivo.Archivo;
import com.ad.ej1_1.lista.archivo.TipoArchivo;

/**
 * Lista contenidos de directorios.
 */
public class Lista {

    private Lista() {}

    /**
     * Comprueba si un archivo es de alguno de los tipos indicados.
     * @param ruta - El archivo a comprobar.
     * @param filtrados - La lista de tipos a comprobar.
     * @return true, si es de alguno de los tipos
     */
    private static boolean descartaTipo(Path ruta, TipoArchivo[] filtrados) {
        return !Arrays.stream(filtrados).anyMatch(tipo -> tipo.es(ruta));
    }

    /**
     * Devuelve los archivos que son estrictamente contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param limite - Límite máximo de archivos que se quieren devolver.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> listar(Path dir, long limite, TipoArchivo ... filtrados) throws IOException {
        if(!Files.isDirectory(dir)) throw new RuntimeException("El argumento debe ser un directorio");

        return Files.list(dir)
                    .filter(f -> Lista.descartaTipo(f, filtrados))
                    .map(Archivo::new).limit(limite);
    } 

    /**
     * Devuelve los archivos que son estrictamente contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> listar(Path dir, TipoArchivo ... filtrados)  throws IOException {
        return listar(dir, Long.MAX_VALUE);
    }

    /**
     * Devuelve los archivos que contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param limite - Límite máximo de archivos que se quieren devolver.
     * @param prof - Profundidad máxima que se revisa para obtener archivos.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> recorrer(Path dir, long limite, int prof, TipoArchivo ... filtrados) throws IOException {
        if(!Files.isDirectory(dir)) throw new RuntimeException("El argumento debe ser un directorio");

        return Files.walk(dir, prof)
                        .filter(f -> Lista.descartaTipo(f, filtrados))
                        .map(Archivo::new).limit(limite);
    }

    /**
     * Devuelve los archivos que contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param limite - Límite máximo de archivos que se quieren devolver.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> recorrer(Path dir, long limite, TipoArchivo ... filtrados) throws IOException {
        return recorrer(dir, limite, Integer.MAX_VALUE, filtrados);
    }

    /**
     * Devuelve los archivos que contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param prof - Profundidad máxima que se revisa para obtener archivos.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> recorrer(Path dir, int prof, TipoArchivo ... filtrados) throws IOException {
        return recorrer(dir, Long.MAX_VALUE, prof, filtrados);
    }

    /**
     * Devuelve los archivos que contenidos por un directorio.
     * y enlaces simbólicos.
     * @param dir - Directorio que se lista.
     * @param filtrados - Los tipos de archivo que se quieren filtrar.
     * @return Un flujo con los archivos requeridos.
     * @throws IOException Cuando no puede revisarse el directorio.
     */
    public static Stream<Archivo> recorrer(Path dir, TipoArchivo ... filtrados) throws IOException {
        return recorrer(dir, Long.MAX_VALUE, Integer.MAX_VALUE, filtrados);
    }
}
