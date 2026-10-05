package modelo.dto;

import modelo.cartas.TipoAtaque;
import modelo.cartas.TipoClima;
import modelo.cartas.TipoEfecto;

public class CartaDTO {
    private String nombre;
    private String tipo;
    private Integer fuerza;
    private TipoAtaque tipoAtaque;
    private TipoClima tipoClima;
    private TipoEfecto tipoEfecto;

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public TipoAtaque getTipoAtaque() {
        return tipoAtaque;
    }

    public TipoClima getTipoClima() {
        return tipoClima;
    }

    public TipoEfecto getTipoEfecto() {
        return tipoEfecto;
    }
}