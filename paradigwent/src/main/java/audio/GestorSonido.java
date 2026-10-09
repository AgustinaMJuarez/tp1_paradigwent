package audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class GestorSonido {
    private final AudioClip click;
    private MediaPlayer musicaFondo;

    public GestorSonido() {
        click = new AudioClip(
                getClass().getResource("/sonidos/clicSelection.wav").toExternalForm()
        );

        URL musica = getClass().getResource("/sonidos/bienvenida.mp3");

        musicaFondo = new MediaPlayer(
                new Media(musica.toExternalForm())
        );

        musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void reproducirClick() {
        click.play();
    }

    public void reproducirMusica() {
        musicaFondo.play();
    }

    public void pausarMusica() {
        musicaFondo.pause();
    }
}