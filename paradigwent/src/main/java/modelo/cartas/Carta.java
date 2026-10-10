package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public abstract class Carta {
    private final String nombre;

    public Carta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void jugar(Jugador jugador, Tablero tablero);

    public boolean requiereLinea() {
        return false;
    }

    public void jugar(Jugador jugador, Tablero tablero, TipoAtaque linea) {
        jugar(jugador, tablero);
    }

}
