package modelo.cartas;

import modelo.Jugador;
import modelo.Tablero;

public class Clima extends Carta{
    private final TipoClima tipoClima;

    public Clima(String nombre, TipoClima tipoClima) {
        super(nombre);
        this.tipoClima = tipoClima;
    }

    public Clima(String nombre) {
        super();
    }

    public TipoClima getTipoClima() {
        return tipoClima;
    }

    public boolean afecta(TipoAtaque ataque) {
        if (tipoClima == TipoClima.ESCARCHA && ataque == TipoAtaque.CUERPO_A_CUERPO) {
            return true;
        }
        if (tipoClima == TipoClima.NIEBLA && ataque == TipoAtaque.DISTANCIA) {
            return true;
        }
        if (tipoClima == TipoClima.LLUVIA && ataque == TipoAtaque.ASEDIO) {
            return true;
        }
        return false;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        tablero.cambiarClima(this, jugador);
    }
}
