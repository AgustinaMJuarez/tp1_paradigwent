package modelo;

import modelo.cartas.Carta;
import modelo.cartas.Criatura;

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

        if (jugador.getMano().isEmpty()) {
            return true;
        }

        if (oponente.yaPaso() && fuerzaAutomata > fuerzaOponente) {
            return true;
        }

        if (fuerzaOponente >= 8 && fuerzaAutomata >= fuerzaOponente + 8) {
            return true;
        }

        if (noPuedeAlcanzar(partida)){
            return true;
        }

        return false;
    }

    private boolean noPuedeAlcanzar(Partida partida) {
        int fuerzaAutomata = jugador.calcularFuerza(partida.getTablero());
        int fuerzaOponente = oponente.calcularFuerza(partida.getTablero());

        return fuerzaAutomata >= 8 && fuerzaOponente >= fuerzaAutomata + 8;
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
