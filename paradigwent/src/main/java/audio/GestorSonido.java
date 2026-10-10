package audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.cartas.Carta;
import modelo.cartas.Clima;
import modelo.cartas.Efecto;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GestorSonido {

    private final AudioClip click;
    private final MediaPlayer musicaFondo;

    private final Map<Class<? extends Carta>, AudioClip> sonidosCartas;

    public GestorSonido() {

        click = cargarAudioClip("/sonidos/clicSelection.wav");

        sonidosCartas = new HashMap<>();

        sonidosCartas.put(
                Clima.class,
                cargarAudioClip("/sonidos/clima.wav")
        );

        sonidosCartas.put(
                Efecto.class,
                cargarAudioClip("/sonidos/efecto.wav")
        );

        URL musica = getClass().getResource("/sonidos/bienvenida.mp3");

        if (musica == null) {
            throw new IllegalStateException(
                    "No se encontró /sonidos/bienvenida.mp3"
            );
        }

        musicaFondo = new MediaPlayer(
                new Media(musica.toExternalForm())
        );

        musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private AudioClip cargarAudioClip(String ruta) {

        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            throw new IllegalStateException(
                    "No se encontró el sonido: " + ruta
            );
        }

        return new AudioClip(recurso.toExternalForm());
    }

    public void reproducirClick() {
        click.play();
    }

    public void reproducirSonidoCarta(Carta carta) {

        AudioClip sonido = sonidosCartas.get(carta.getClass());

        if (sonido != null) {
            sonido.play();
        }
    }

    public void reproducirMusica() {
        musicaFondo.play();
    }

    public void pausarMusica() {
        musicaFondo.pause();
    }
}