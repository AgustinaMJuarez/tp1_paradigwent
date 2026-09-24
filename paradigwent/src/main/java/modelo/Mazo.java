package modelo.cartas;

import modelo.Faccion;

import java.util.ArrayList;
import java.util.List;

public class Mazo {
    private Faccion faccion;
    private List<Carta> cartas;

    public Mazo(Faccion faccion, List<Carta> cartas) {
        this.faccion = faccion;
        this.cartas = new ArrayList<Carta>();
    }

    public Carta robarCarta() {
        return cartas.remove(0);
    }

    public void sortearMazo() {
        //..
    }

}
