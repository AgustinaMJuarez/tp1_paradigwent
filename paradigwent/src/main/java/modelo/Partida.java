package modelo;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private final Jugador jugador;
    private final Jugador enemigo;
    private Ronda rondaActual;

    public Partida(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    public void iniciarPartida() {
        //..
    }

    public boolean estaTerminada() {
        return jugador.estaDerrotado() || enemigo.estaDerrotado();
    }

    public Jugador ganador() {
        //..
    }

    public void rendirse() {
        //..
    }

}
