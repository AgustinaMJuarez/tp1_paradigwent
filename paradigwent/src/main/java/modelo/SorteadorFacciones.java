package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SorteadorFacciones {

    public Faccion[] sortear() {
        List<Faccion> facciones =
                new ArrayList<>(Arrays.asList(Faccion.values()));

        Collections.shuffle(facciones);

        return new Faccion[] {
                facciones.get(0),
                facciones.get(1),
                facciones.get(2)
        };
    }
}
