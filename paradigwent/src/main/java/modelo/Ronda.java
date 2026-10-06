package modelo;

import modelo.cartas.Carta;
import modelo.cartas.Efecto;
import modelo.cartas.TipoAtaque;

public class Ronda {
    private final Jugador jugador;
    private final Jugador enemigo;
    private final Tablero tablero;
    private Jugador jugadorActual;

    public Ronda(Jugador jugador, Jugador enemigo, Jugador jugadorActual, Tablero tablero) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.jugadorActual = jugadorActual;
        this.tablero = tablero;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }


    private void cambiarJugador() {
        Jugador siguiente;

        if (jugadorActual == jugador) {
            siguiente = enemigo;
        } else {
            siguiente = jugador;
        }

        if (!siguiente.yaPaso()) {
            jugadorActual = siguiente;
        }
    }

    public void jugarCarta(Carta carta){
        jugadorActual.jugarCarta(carta, tablero);
        cambiarJugador();
    }

    public void jugarEfecto(Efecto efecto, Jugador jugadorObjetivo, TipoAtaque tipoAtaque) {
        jugadorActual.jugarEfecto(efecto, tablero, jugadorObjetivo, tipoAtaque);
        cambiarJugador();
    }

    public void pasar() {
        jugadorActual.pasarTurno();
        cambiarJugador();
    }

    public boolean estaTerminada() {
        return jugador.yaPaso() && enemigo.yaPaso();
    }


    public Jugador determinarGanador(){
        int fuerzaJugador = jugador.calcularFuerza(tablero);
        int fuerzaEnemigo = enemigo.calcularFuerza(tablero);

        if (fuerzaJugador > fuerzaEnemigo) {
            return jugador;
        }

        if (fuerzaEnemigo > fuerzaJugador) {
            return enemigo;
        }

        return null;
    }

    public void finalizar(){
        Jugador ganador = determinarGanador();
        if (ganador == jugador) {
            enemigo.perderVida();
        } else if (ganador == enemigo) {
            jugador.perderVida();
        }else {
            jugador.perderVida();
            enemigo.perderVida();
        }
        jugador.resetearPaso();
        enemigo.resetearPaso();
        jugador.descartarCartasLineas();
        enemigo.descartarCartasLineas();
        tablero.limpiarClimaEfecto();
    }

}

