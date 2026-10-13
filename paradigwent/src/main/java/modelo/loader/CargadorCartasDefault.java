package modelo.loader;

import modelo.cartas.*;
import modelo.dto.CartaDTO;

public class CargadorCartasDefault implements CargadorCartas {

    private Habilidad crearHabilidad(TipoEfecto tipoEfecto) {
        return switch (tipoEfecto) {
            case QUEMADURA ->
                    new Quemadura();

            case CUERNO_DE_COMANDANTE ->
                    new CuernoComandante();
        };
    }

    @Override
    public Carta crear(CartaDTO cartaDto) {
        switch (cartaDto.getTipo()) {

            case "CRIATURA":
                if (cartaDto.getTipoEfecto() != null) {
                    return new Criatura(
                            cartaDto.getNombre(),
                            cartaDto.getFuerza(),
                            cartaDto.getTipoAtaque(),
                            crearHabilidad(cartaDto.getTipoEfecto())
                    );
                }
                return new Criatura(
                        cartaDto.getNombre(),
                        cartaDto.getFuerza(),
                        cartaDto.getTipoAtaque()
                );

            case "CLIMA":
                return new Clima(cartaDto.getNombre(), cartaDto.getTipoClima());

            case "EFECTO":
                return new Efecto(cartaDto.getNombre(), crearHabilidad(cartaDto.getTipoEfecto()));

            default:
                throw new IllegalArgumentException(
                        "Tipo de carta no reconocido"
                );
        }
    }

}
