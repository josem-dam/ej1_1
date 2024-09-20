package es.iescastillodeluna.ad;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of(System.getProperty("user.home"), "Seguimiento.odt");
        Archivo archivo = new Archivo(ruta);
        System.out.println(archivo.getTamanno());
    }
}