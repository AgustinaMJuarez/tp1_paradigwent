package modelo;

import modelo.cartas.Carta;
public class Automata {
    private final Jugador jugador;
    private final Jugador oponente;

    public Automata(Jugador jugador, Jugador oponente) {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public boolean convienePasar(Partida partida) {
        int fuerzaAutomata = jugador.calcularFuerza(partida.getTablero());
        int fuerzaOponente = oponente.calcularFuerza(partida.getTablero());

        if (oponente.yaPaso() && fuerzaAutomata > fuerzaOponente) {
            return true;
        }

        if (jugador.getMano().isEmpty()) {
            return true;
        }
        return false;
    }

    public void jugarTurno(Partida partida) {
        if (convienePasar(partida)) {
            partida.pasarTurno();
            return;
        }
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
