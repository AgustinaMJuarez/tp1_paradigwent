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

    public Jugador(Mazo mazo) {
        this.vidas = 3;
        this.mazo = mazo;
        this.mano = new ArrayList<Carta>();
        this.pilaDescarte = new ArrayList<Carta>();
        this.lineas = new HashMap<>();
        this.paso = false;
    }


    public List<Criatura> getCriaturasEnLinea(TipoAtaque tipoAtaque) {
        return Collections.unmodifiableList(
                lineas.getOrDefault(tipoAtaque, Collections.emptyList())
        );
    }

    public List<Carta> getMano() {
        return mano;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Faccion getFaccion() {
        return mazo.getFaccion();
    }

    public int getVidas() {
        return vidas;
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

    public void jugarEfecto(
            Efecto efecto,
            Tablero tablero,
            Jugador jugadorObjetivo,
            TipoAtaque tipoAtaque) {

        if (!tieneCarta(efecto)) {
            return;
        }

        mano.remove(efecto);
        efecto.jugar(this, tablero, jugadorObjetivo, tipoAtaque);
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


    public void repartirMano() {
        for (int i =0; i < 10; i++) {
            mano.add(mazo.robarCarta());
        }
    }

    public int fuerzaMaximaDeCriatura() {
        int maxima = 0;

        for (List<Criatura> criaturas : lineas.values()) {
            for (Criatura criatura : criaturas) {
                maxima = Math.max(maxima, criatura.getFuerza());
            }
        }

        return maxima;
    }

    public void destruirCriaturasDeFuerza(int fuerzaMaxima) {
        for (List<Criatura> criaturas : lineas.values()) {
            Iterator<Criatura> iterator = criaturas.iterator();

            while (iterator.hasNext()) {
                Criatura criatura = iterator.next();

                if (criatura.getFuerza() == fuerzaMaxima) {
                    pilaDescarte.add(criatura);
                    iterator.remove();
                }
            }
        }
    }

    public int calcularFuerza(Tablero tablero) {
        int fuerza = 0;
        Clima climaTablero = tablero.getClima();
        for (Map.Entry<TipoAtaque, List<Criatura>> entry : lineas.entrySet()) {
            TipoAtaque tipoAtaque = entry.getKey();
            List<Criatura> criaturas = entry.getValue();

            for (Criatura criatura : criaturas) {
                int fuerzaCriatura = criatura.getFuerza();

                if (climaTablero != null && climaTablero.afecta(tipoAtaque)) {
                    fuerzaCriatura = 1;
                }

                if (tablero.tieneCuerno(this, tipoAtaque)) {
                    fuerzaCriatura *= 2;
                }

                fuerza += fuerzaCriatura;
            }
        }
        return fuerza;
    }

}
