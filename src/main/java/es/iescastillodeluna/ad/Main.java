package es.iescastillodeluna.ad;
import java.nio.file.Path;

import es.iescastillodeluna.ad.archivo.Archivo;

public class Main {
    
    /**
     * Imprime en la consola la información del archivo.
     * @param archivo
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
        Lista.recorrer(ruta, 15L, 3).forEach(Main::imprimeLinea);
    }
}