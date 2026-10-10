package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class CuernoComandante implements Habilidad {
    @Override
    public void aplicar(Jugador jugador, Tablero tablero, Jugador jugadorObjetivo,TipoAtaque tipoAtaque) {
        tablero.agregarCuerno(jugadorObjetivo, tipoAtaque);
    }
}
