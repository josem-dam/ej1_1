package com.ad.ej1_1;
import java.nio.file.Path;

import com.ad.ej1_1.lista.Lista;
import com.ad.ej1_1.lista.archivo.Archivo;
import com.ad.ej1_1.lista.archivo.TipoArchivo;

/**
 * Ilustra cómo se usa {@link com.ad.ej1_1.lista.Lista} en una interfaz de línea de comandos.
 */
public class Main {
    
    /**
     * Imprime en la consola la información del archivo.
     * @param archivo - El archivo del que se desea imprimir la información
     */
    public static void imprimeLinea(Archivo archivo) {
        String formatoArchivo = "%-60s (%10s) %s %10s\n",
               formatoDirectorio = "%s\n";

        if(archivo.esDirectorio()) {
            System.out.printf(formatoDirectorio, archivo);
        }
        else {
            System.out.printf(formatoArchivo, archivo, archivo.getFecha(),archivo.getPropietario(), archivo.getTamanno());
        }
    }
    public static void main(String[] args) throws Exception {
        Path ruta = Path.of(System.getProperty("user.home"));
        Lista.listar(ruta, 15L, TipoArchivo.OCULTO, TipoArchivo.SIMBOLICO).forEach(Main::imprimeLinea);
    }
}