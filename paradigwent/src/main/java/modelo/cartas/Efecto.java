package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class Efecto extends Carta{
    private final Habilidad habilidad;

    public Efecto(String nombre, Habilidad habilidad) {
        super(nombre);
        this.habilidad = habilidad;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        // ...
    }
    public void jugar(
            Jugador jugador,
            Tablero tablero,
            Jugador jugadorObjetivo,
            TipoAtaque tipoAtaque) {

        tablero.agregarEfecto(jugador, this);

        habilidad.aplicar(jugador,tablero,jugadorObjetivo,tipoAtaque);
    }
}
