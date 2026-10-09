package modelo.dto;

import modelo.Faccion;
import java.util.List;

public class MazoDTO {
    private Faccion faccion;
    private List<CartaDTO> cartas;

    public Faccion getFaccion() {
        return faccion;
    }

    public List<CartaDTO> getCartas() {
        return cartas;
    }
}

