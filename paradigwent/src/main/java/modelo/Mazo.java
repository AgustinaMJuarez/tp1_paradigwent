package modelo;

import modelo.cartas.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private final Faccion faccion;
    private final List<Carta> cartas;

    public Mazo(Faccion faccion, List<Carta> cartas) {
        this.faccion = faccion;
        this.cartas = new ArrayList<>(cartas);
    }

    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.removeFirst();
    }

    public void sortearMazo() {
        Collections.shuffle(cartas);
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }

    public Faccion getFaccion() {
        return faccion;
    }
}

