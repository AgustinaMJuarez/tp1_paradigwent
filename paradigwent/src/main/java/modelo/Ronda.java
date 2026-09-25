package modelo;

import modelo.cartas.Carta;

public class Ronda {
    private final Jugador jugador;
    private final Jugador enemigo;
    private final Tablero tablero;
    private Jugador jugadorActual;

    public Ronda(Jugador jugador, Jugador enemigo, Jugador jugadorActual) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.jugadorActual = jugadorActual;
        this.tablero = new Tablero(jugador,enemigo);
    }

    public Carta jugarCarta(Carta carta) {
        /* .. */
    }

    public void pasar() {
        /* .. */
    }

    public boolean estaTerminada() {
        /* .. */
    }

    public void finalizar() {

    }


}

