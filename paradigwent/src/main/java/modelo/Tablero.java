package modelo;

import modelo.cartas.Clima;

public class Tablero {
    private Clima clima;
    private final Jugador jugador;
    private final Jugador enemigo;

    public Tablero(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
    }



}
