package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class Efecto extends Carta{
    private final TipoEfecto tipoEfecto;

    public Efecto(String nombre, TipoEfecto tipoEfecto) {
        super(nombre);
        this.tipoEfecto = tipoEfecto;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        // aplicar efecto
    }
}
