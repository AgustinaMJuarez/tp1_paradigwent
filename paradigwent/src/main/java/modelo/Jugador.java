package modelo;

import modelo.cartas.Carta;
import modelo.cartas.Mazo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int vidas;
    private final Mazo mazo;
    private final List<Carta> mano;
    private final List<Carta> pilaDescarte;

    public Jugador(Mazo mazo) {
        this.vidas = 3;
        this.mazo = mazo;
        this.mano = new ArrayList<Carta>();
        this.pilaDescarte = new ArrayList<Carta>();
    }


    public void perderVida() {
        this.vidas -= 1;
    }

    public boolean estaDerrotado() {
        if (this.vidas == 0) {
            return true;
        }
        return false;
    }
    public void descartar(Carta carta) {
      //..
    }

    public void sacarDeLaMano(Carta carta) {
        //.. en duda con el nombre (la idea es que si saque de la mano para decidir la jugada
    }

    public void repartirMano() {
        //..
    }



}
