package modelo;

import modelo.cartas.Carta;
import modelo.cartas.Efecto;
import modelo.cartas.TipoAtaque;

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
        if (jugador.estaDerrotado() && enemigo.estaDerrotado()) {
            return null;
        }

        if (jugador.estaDerrotado()) {
            return enemigo;
        }

        if (enemigo.estaDerrotado()) {
            return jugador;
        }

        return null;
    }

    public void jugarCarta(Carta carta) {
        jugarCarta(carta, null);
    }

    public void jugarCarta(Carta carta, TipoAtaque linea) {
        rondaActual.jugarCarta(carta, linea);
        manejarRondas();
    }

    public void pasarTurno() {
        rondaActual.pasar();
        manejarRondas();
    }

    private Carta manejarTurno() {
        if (rondaActual.getJugadorActual() == enemigo) {
            return automata.jugarTurno(this);
        }

        return null;
    }

    public Carta ejecutarTurnoEnemigo() {
        return manejarTurno();
    }

    public Jugador getJugadorActual() {
        return rondaActual.getJugadorActual();
    }

    public Jugador getEnemigo() {
        return enemigo;
    }


}
