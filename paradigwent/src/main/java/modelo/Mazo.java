package modelo;

import modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private Faccion faccion;
    private List<Carta> cartas;

    public Mazo(Faccion faccion, List<Carta> cartas) {
        this.faccion = faccion;
        this.cartas = new ArrayList<>(cartas);
    }

    public Carta robarCarta() {
        return cartas.remove(0);
    }

    public void sortearMazo() {
        Collections.shuffle(cartas);
    }

    public int cantidadCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }
}

