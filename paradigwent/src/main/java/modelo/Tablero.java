package modelo;

import modelo.cartas.Clima;
import modelo.cartas.TipoAtaque;

import java.util.*;

public class Tablero {
    private Clima clima;
    private Jugador creadorClima;
    private final Jugador jugador;
    private final Jugador enemigo;
    private final Map<Jugador, Set<TipoAtaque>> lineasConCuerno;

    public Tablero(Jugador jugador, Jugador enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.lineasConCuerno = new HashMap<>();
    }

    public Clima getClima() {
        return clima;
    }

    public void cambiarClima(Clima nuevoClima, Jugador jugadorClima){
        if (clima != null){
            creadorClima.descartarCarta(clima);
        }
        clima = nuevoClima;
        creadorClima = jugadorClima;
    }

    public void limpiarClimaEfecto() {
        if (clima != null) {
            creadorClima.descartarCarta(clima);
        }
        clima = null;
        creadorClima = null;
        lineasConCuerno.clear();
    }

    public void agregarCuerno(Jugador jugadorObjetivo, TipoAtaque tipoAtaque) {
        lineasConCuerno
                .computeIfAbsent(jugadorObjetivo, j -> new HashSet<>())
                .add(tipoAtaque);
    }

    public boolean tieneCuerno(Jugador jugador, TipoAtaque tipoAtaque) {
        return lineasConCuerno
                .getOrDefault(jugador, Collections.emptySet())
                .contains(tipoAtaque);
    }

    public void aplicarQuemadura() {
        int fuerzaMaximaJugador = jugador.fuerzaMaximaDeCriatura();
        int fuerzaMaximaEnemigo = enemigo.fuerzaMaximaDeCriatura();

        int fuerzaMaxima = Math.max(
                fuerzaMaximaJugador,
                fuerzaMaximaEnemigo
        );

        if (fuerzaMaxima == 0) {
            return;
        }

        jugador.destruirCriaturasDeFuerza(fuerzaMaxima);
        enemigo.destruirCriaturasDeFuerza(fuerzaMaxima);
    }


}
