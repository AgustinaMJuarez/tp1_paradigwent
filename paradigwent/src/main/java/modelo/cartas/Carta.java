package modelo.cartas;

public abstract class Carta {
    private final String nombre;

    public Carta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
