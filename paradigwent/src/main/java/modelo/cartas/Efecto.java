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
    public boolean requiereLinea() {
        return habilidad.requiereLinea();
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        jugar(jugador, tablero, null);
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, TipoAtaque linea) {
        tablero.agregarEfecto(jugador, this);
        habilidad.aplicar(jugador, tablero, jugador, linea);
    }
}
