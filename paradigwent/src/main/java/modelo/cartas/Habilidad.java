package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public interface Habilidad {
    void aplicar(Jugador jugador, Tablero tablero, Jugador jugadorObjetivo, TipoAtaque tipoAtaque);
}
