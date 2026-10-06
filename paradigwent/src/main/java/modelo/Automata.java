package modelo;

import modelo.cartas.Carta;
public class Automata {
    private final Jugador jugador;

    public Automata(Jugador jugador) {
        this.jugador = jugador;
    }

    public void jugarTurno(Partida partida) {
        Carta carta = elegirCarta();

        if (carta != null) {
            partida.jugarCarta(carta);
        } else {
            partida.pasarTurno();
        }
    }

    private Carta elegirCarta() {
        if (jugador.getMano().isEmpty()) {
            return null;
        }

        return jugador.getMano().get(0);
    }
}
