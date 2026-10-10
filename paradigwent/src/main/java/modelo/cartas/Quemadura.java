package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class Quemadura implements Habilidad {
    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Jugador jugadorObjetivo, TipoAtaque tipoAtaque) {
        tablero.aplicarQuemadura();
    }
}
