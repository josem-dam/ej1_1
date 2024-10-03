package com.ad.ej1_1.lista.archivo;

/**
 * Modela el tamaño de un archivo.
 */
public class Tamanno {

    /**
     * Letra que identifica a cada unidad ([b]yte, [k]ilobyte, etc).
     */
    private static char[] multiplos = {'b', 'k', 'm', 'g', 't', 'p', 'e', 'z'};
    /**
     * Factor de conversión entre unidades
     */
    private static int factor = 1024;

    /**
     * Tamaño en bytes.
     */
    private long tamanno;

    /**
     * Constructor de la clase
     * @param tamanno - El tamaño en bytes.
     */
    public Tamanno(long tamanno) {
        setTamanno(tamanno);
    }

    /**
     * Setter de tamaño
     * @param tamanno El tamaño en bytes
     */
    private void setTamanno(long tamanno) {
        this.tamanno = tamanno;
    }

    /**
     * Getter de tamaño
     * @return El tamaño en bytes.
     */
    public long getTamanno() {
        return tamanno;
    }

    /**
     * Devuelve el tamaño en una unidad legible. Por ejemplo,
     * en vez de 10240 bytes devolverá 10k.
     * @return El tamaño con su unidad correspondiente.
     */
    public String getTamannoLegible() {
        double resultado = tamanno;
        char unidad = 'b';

        for (char c : multiplos) {
            unidad = c;
            if(resultado < factor) break;
            resultado /= factor;
        }

        return String.format("%.3f%c", resultado, unidad);
    }

    @Override
    public String toString() {
        return getTamannoLegible();
    }

    @Override
    public boolean equals(Object obj) {
        return getTamanno() == ((Tamanno) obj).getTamanno();
    }
}
