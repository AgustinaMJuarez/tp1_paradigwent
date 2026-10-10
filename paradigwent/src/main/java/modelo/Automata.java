package modelo;

import modelo.cartas.Carta;
import modelo.cartas.Criatura;
import modelo.cartas.TipoAtaque;

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


    public Carta jugarTurno(Partida partida) {
        if (convienePasar(partida)) {
            partida.pasarTurno();
            return null;
        }
        Carta carta = elegirCarta();

        if (carta != null) {
            if (carta.requiereLinea()) {
                partida.jugarCarta(carta, elegirLinea());
            } else {
                partida.jugarCarta(carta);
            }
        } else {
            partida.pasarTurno();
        }

        return carta;
    }

    private Carta elegirCarta() {
        if (jugador.getMano().isEmpty()) {
            return null;
        }

        return jugador.getMano().getFirst();
    }

    private TipoAtaque elegirLinea() {
        TipoAtaque mejor = TipoAtaque.CUERPO_A_CUERPO;
        int fuerzaMejor = -1;

        for (TipoAtaque tipo : TipoAtaque.values()) {
            int fuerzaLinea = 0;
            for (Criatura criatura : jugador.getCriaturasEnLinea(tipo)) {
                fuerzaLinea += criatura.getFuerza();
            }
            if (fuerzaLinea > fuerzaMejor) {
                fuerzaMejor = fuerzaLinea;
                mejor = tipo;
            }
        }
        return mejor;
    }
}
