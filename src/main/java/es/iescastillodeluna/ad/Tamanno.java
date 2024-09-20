package es.iescastillodeluna.ad;

public class Tamanno {

    private static char[] multiplos = {'b', 'k', 'm', 'g', 't', 'p', 'e', 'z'};
    private static int factor = 1024;

    private long tamanno;

    public Tamanno(long tamanno) {
        setTamanno(tamanno);
    }

    public void setTamanno(long tamanno) {
        this.tamanno = tamanno;
    }

    public long getTamanno() {
        return tamanno;
    }

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
