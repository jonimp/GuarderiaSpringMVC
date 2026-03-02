package prog2.guarderiaspring;

public class EstadoZona {

    private int total;
    private int ocupados;
    private int porcentaje;

    public EstadoZona(int total, int ocupados) {
        this.total = total;
        this.ocupados = ocupados;
        this.porcentaje = total == 0 ? 0 : (ocupados * 100) / total;
    }

    public int getTotal() {
        return total;
    }

    public int getOcupados() {
        return ocupados;
    }

    public int getPorcentaje() {
        return porcentaje;
    }
}
