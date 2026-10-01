package modelo;

import modelo.cartas.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jugador {
    private int vidas;
    private final Mazo mazo;
    private final List<Carta> mano;
    private final List<Carta> pilaDescarte;
    private Map<TipoAtaque, List<Criatura>> lineas;
    private boolean paso;

    public Jugador(Mazo mazo) {
        this.vidas = 3;
        this.mazo = mazo;
        this.mano = new ArrayList<Carta>();
        this.pilaDescarte = new ArrayList<Carta>();
        this.lineas = new HashMap<>();
        this.paso = false;
    }

    public boolean tieneCarta(Carta carta) {
        return mano.contains(carta);
    }

    public void jugarCarta(Carta carta){
        if (!tieneCarta(carta)) {
            return;
        }
        if (carta instanceof Criatura) {
            jugarCriatura((Criatura) carta);
        } else if (carta instanceof Clima) {
            jugarClima((Clima) carta);
        } else if (carta instanceof Efecto) {
            jugarEfecto((Efecto) carta);
        }
    }

    private void jugarCriatura(Criatura criatura) {
        switch (criatura.getTipoAtaque()) {
            case CUERPO_A_CUERPO:
                lineas.computeIfAbsent(TipoAtaque.CUERPO_A_CUERPO, k -> new ArrayList<>()).add(criatura);
                break;

            case DISTANCIA:
                lineas.computeIfAbsent(TipoAtaque.DISTANCIA, k -> new ArrayList<>()).add(criatura);
                break;

            case ASEDIO:
                lineas.computeIfAbsent(TipoAtaque.ASEDIO, k -> new ArrayList<>()).add(criatura);
                break;
        }
    }

    private void jugarClima(Clima clima){

    }

    private void jugarEfecto(Efecto efecto){

    }



    public void perderVida() {
        this.vidas -= 1;
    }

    public boolean estaDerrotado() {
        return this.vidas == 0;
    }

    public void descartar(Carta carta) {
      if (!tieneCarta(carta)){
          return;
      }
      mano.remove(carta);
      pilaDescarte.add(carta);
    }

    public void pasarTurno() {
        paso = true;
    }

    public boolean yaPaso() {
        return paso;
    }

    public void sacarDeLaMano(Carta carta) {
        //.. en duda con el nombre (la idea es que si saque de la mano para decidir la jugada
    }

    public void repartirMano() {
        //..
    }

    public void calcularFuerza() {
        
    }


}
