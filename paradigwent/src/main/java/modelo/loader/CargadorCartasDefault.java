package modelo.loader;

import modelo.cartas.*;
import modelo.dto.CartaDTO;

public class CargadorCartasDefault implements CargadorCartas {

    @Override
    public Carta crear(CartaDTO cartaDto) {
        switch (cartaDto.getTipo()) {

            case "CRIATURA":
                return new Criatura(
                        cartaDto.getNombre(),
                        cartaDto.getFuerza(),
                        cartaDto.getTipoAtaque()
                );

            case "CLIMA":
                return new Clima(cartaDto.getNombre(), cartaDto.getTipoClima());

            case "EFECTO":
                return new Efecto(cartaDto.getNombre(), cartaDto.getTipoEfecto());

            default:
                throw new IllegalArgumentException(
                        "Tipo de carta no reconocido"
                );
        }
    }

}
