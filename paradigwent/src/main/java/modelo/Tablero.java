package modelo;

import modelo.cartas.Clima;

public class Tablero {
    private Clima clima;
    private Jugador creadorClima;
    private final Jugador jugador;
    private final Jugador enemigo;

    public Tablero(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    public Clima getClima() {
        return clima;
    }

    public void cambiarClima(Clima nuevoClima, Jugador jugadorClima){
        if (clima != null){
            creadorClima.descartarCarta(clima);
        }
        clima = nuevoClima;
        creadorClima = jugadorClima;
    }

    public void limpiarClima() {
        if (clima != null) {
            creadorClima.descartarCarta(clima);
        }
        clima = null;
        creadorClima = null;
    }
}
