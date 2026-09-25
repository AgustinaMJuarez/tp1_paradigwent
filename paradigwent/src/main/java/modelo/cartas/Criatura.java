package modelo.cartas;

public class Criatura extends Carta{
    private final int fuerza;
    private final TipoAtaque tipoAtaque;

    public Criatura(String nombre, int fuerza, TipoAtaque tipoAtaque) {
        super(nombre);
        this.fuerza = fuerza;
        this.tipoAtaque = tipoAtaque;
    }

    public TipoAtaque getTipoAtaque() {
        return tipoAtaque;
    }

    public int getFuerza() {
        return fuerza;
    }
}
