package audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import modelo.cartas.Carta;
import modelo.cartas.Clima;
import modelo.cartas.Criatura;
import modelo.cartas.Efecto;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GestorSonido {

    private final AudioClip click;
    private final AudioClip musicaGanador;
    private final AudioClip musicaPerdedor;
    private final AudioClip finRound;

    private final MediaPlayer musicaFondo;

    private static final double VOLUMEN_PERDEDOR = 0.5;
    private static final double VOLUMEN_FONDO = 0;

    private final Map<Class<? extends Carta>, AudioClip> sonidosCartas;

    public GestorSonido() {

        click = cargarAudioClip("/sonidos/clicSelection.wav");
        musicaGanador = cargarAudioClip("/sonidos/win.wav");
        musicaPerdedor = cargarAudioClip("/sonidos/lose.wav");
        finRound = cargarAudioClip("/sonidos/endRound.wav");

        musicaPerdedor.setVolume(VOLUMEN_PERDEDOR);

        sonidosCartas = new HashMap<>();

        sonidosCartas.put(
                Criatura.class,
                cargarAudioClip("/sonidos/cardThrown.wav")
        );

        sonidosCartas.put(
                Clima.class,
                cargarAudioClip("/sonidos/effect.wav")
        );

        sonidosCartas.put(
                Efecto.class,
                cargarAudioClip("/sonidos/effect.wav")
        );

        URL musica = cargarRecurso("/sonidos/bienvenida.mp3");

        musicaFondo = new MediaPlayer(
                new Media(musica.toExternalForm())
        );

        musicaFondo.setVolume(VOLUMEN_FONDO);

        musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private AudioClip cargarAudioClip(String ruta) {
        return new AudioClip(
                cargarRecurso(ruta).toExternalForm()
        );
    }

    private URL cargarRecurso(String ruta) {

        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            throw new IllegalStateException(
                    "No se encontró el recurso: " + ruta
            );
        }

        return recurso;
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

    public void reproducirGanador() {
        musicaGanador.play();
    }

    public void reproducirPerdedor() {
        musicaPerdedor.play();
    }

    public void reproducirFinRound() {
        finRound.play();
    }

    public void reproducirMusica() {
        musicaFondo.play();
    }

    public void pausarMusica() {
        musicaFondo.pause();
    }
}