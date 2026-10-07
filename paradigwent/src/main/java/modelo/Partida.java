package modelo;

import modelo.cartas.Carta;

import java.util.Random;

public class Partida {
    private final Jugador jugador;
    private final Jugador enemigo;
    private Ronda rondaActual;
    private final Tablero tablero;
    private final Automata automata;
    private int numeroRonda;

    public Partida(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.tablero = new Tablero(jugador, enemigo);
        this.automata = new Automata(enemigo, jugador);
    }

    public void iniciarPartida() {
        crearRonda();
        manejarTurno();
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    private void crearRonda() {
        Random random = new Random();
        Jugador jugadorInicial = random.nextBoolean() ? jugador : enemigo;
        this.rondaActual = new Ronda(jugador, enemigo, jugadorInicial, tablero);
        numeroRonda++;
    }

    public int getNumeroRonda() {
        return numeroRonda;
    }

    public void manejarRondas() {
        if (this.estaTerminada()){
            return;
        }

        if (rondaActual == null) {
            crearRonda();
            return;
        }

        if (rondaActual.estaTerminada()) {
            rondaActual.finalizar();
            if (estaTerminada()){
                return;
            }
            crearRonda();

        }

    }

    public boolean estaTerminada() {
        return jugador.estaDerrotado() || enemigo.estaDerrotado();
    }

    public Jugador ganador() {
        if (jugador.estaDerrotado()) {
            return enemigo;
        }else{
            return jugador;
        }
    }

    public void jugarCarta(Carta carta) {
        rondaActual.jugarCarta(carta);
        manejarRondas();
        manejarTurno();
    }

    public void pasarTurno() {
        rondaActual.pasar();
        manejarRondas();
        manejarTurno();
    }

    private void manejarTurno() {
        if (rondaActual.getJugadorActual() == enemigo) {
            automata.jugarTurno(this);
        }
    }

    public Jugador getJugadorActual() {
        return rondaActual.getJugadorActual();
    }

    public Jugador getEnemigo() {
        return enemigo;
    }
}
