package modelo.loader;

import modelo.cartas.Carta;
import modelo.dto.CartaDTO;

public interface CargadorCartas {
    Carta crear(CartaDTO cartaDTO);
}
