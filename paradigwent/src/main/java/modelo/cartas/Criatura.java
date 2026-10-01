package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class Criatura extends Carta{
    private final int fuerza;
    private final TipoAtaque tipoAtaque;

    public Criatura(String nombre, int fuerza, TipoAtaque tipoAtaque) {
        this(nombre, fuerza, tipoAtaque, null);
    }


    public Criatura(String nombre, int fuerza, TipoAtaque tipoAtaque, TipoEfecto habilidad) {
        super(nombre);
        this.fuerza = fuerza;
        this.tipoAtaque = tipoAtaque;
        this.habilidad = habilidad;
    }

    public TipoAtaque getTipoAtaque() {
        return tipoAtaque;
    }

    public int getFuerza() {
        return fuerza;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        jugador.agregarCriatura(this);
    }

    public TipoEfecto getHabilidad() {return habilidad;}

    public boolean tieneHabilidad() { return habilidad != null;}

}
