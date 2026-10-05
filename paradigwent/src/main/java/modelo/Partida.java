package modelo;

import java.util.Random;

public class Partida {
    private final Jugador jugador;
    private final Jugador enemigo;
    private Ronda rondaActual;
    private final Tablero tablero;

    public Partida(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.tablero = new Tablero(jugador, enemigo);
    }

    private void crearRonda() {
        Random random = new Random();
        Jugador jugadorInicial = random.nextBoolean() ? jugador : enemigo;
        this.rondaActual = new Ronda(jugador, enemigo, jugadorInicial, tablero);
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


}
