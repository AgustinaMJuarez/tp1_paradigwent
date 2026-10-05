package modelo;

import modelo.cartas.*;

import java.util.*;

public class Jugador {
    private int vidas;
    private final Mazo mazo;
    private final List<Carta> mano;
    private final List<Carta> pilaDescarte;
    private final Map<TipoAtaque, List<Criatura>> lineas;
    private boolean paso;
    private final Set<TipoAtaque> lineasConCuerno;

    public Jugador(Mazo mazo) {
        this.vidas = 3;
        this.mazo = mazo;
        this.mano = new ArrayList<Carta>();
        this.pilaDescarte = new ArrayList<Carta>();
        this.lineas = new HashMap<>();
        this.paso = false;
        this.lineasConCuerno = new HashSet<>()
    }

    public boolean tieneCarta(Carta carta) {
        return mano.contains(carta);
    }

    public void jugarCarta(Carta carta, Tablero tablero) {
        if (!tieneCarta(carta)) {
            return;
        }
        mano.remove(carta);
        carta.jugar(this, tablero);
    }


    public void agregarCriatura(Criatura criatura) {
        lineas
                .computeIfAbsent(
                        criatura.getTipoAtaque(),
                        k -> new ArrayList<>()
                )
                .add(criatura);
    }

    public void agregarEfecto(Efecto efecto) {

    }


    public void perderVida() {
        this.vidas -= 1;
    }

    public boolean estaDerrotado() {
        return this.vidas == 0;
    }

    public void descartarCarta(Carta carta) {
      pilaDescarte.add(carta);
    }

    public void descartarCartasLineas() {
        for (List<Criatura> listaCriaturas : lineas.values()) {
            pilaDescarte.addAll(listaCriaturas);
            listaCriaturas.clear();
        }

    }

    public void pasarTurno() {
        paso = true;
    }

    public void rendirse() {
        vidas = 0;
    }

    public void resetearPaso() {
        paso = false;
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

    public int calcularFuerza(Tablero tablero) {
        int fuerza = 0;
        Clima climaTablero = tablero.getClima();
        for (Map.Entry<TipoAtaque, List<Criatura>> entry : lineas.entrySet()) {
            TipoAtaque tipoAtaque = entry.getKey();
            List<Criatura> criaturas = entry.getValue();

            for (Criatura criatura : criaturas) {
                if (criatura instanceof Efecto){

                }
                if ((climaTablero != null) && (climaTablero.afecta(tipoAtaque))) {
                    fuerza += 1;
                } else{
                    fuerza += criatura.getFuerza();
                }
            }
        }
        return fuerza;
    }


}
