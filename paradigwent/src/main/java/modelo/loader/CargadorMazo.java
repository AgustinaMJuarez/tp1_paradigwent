package modelo.loader;

import com.google.gson.Gson;
import modelo.Mazo;
import modelo.cartas.Carta;
import modelo.dto.CartaDTO;
import modelo.dto.MazoDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CargadorMazo {

    private final CargadorCartas cargadorCartas;

    public CargadorMazo(CargadorCartas cargadorCartas) {
        this.cargadorCartas = cargadorCartas;
    }

    public Mazo cargar(String ruta) {

        InputStream inputStream = getClass().getResourceAsStream("/mazos/" + ruta);

        if (inputStream == null) {
            throw new IllegalArgumentException(
                    "No se encontró el archivo: " + ruta
            );
        }

        Gson gson = new Gson();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {

            MazoDTO datosMazo = gson.fromJson(reader, MazoDTO.class);

            List<Carta> cartas = new ArrayList<>();

            for (CartaDTO carta : datosMazo.getCartas()) {
                cartas.add(cargadorCartas.crear(carta));
            }

            return new Mazo(datosMazo.getFaccion(), cartas);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el mazo: " + ruta, e);
        }
    }
}