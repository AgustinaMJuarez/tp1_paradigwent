package audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class GestorSonido {
    private final AudioClip click;
    private final AudioClip tirarCarta;
    private final AudioClip musicaGanador;
    private final AudioClip musicaPerdedor;
    private MediaPlayer musicaFondo;

    public GestorSonido() {
        click = new AudioClip(
                getClass().getResource("/sonidos/clicSelection.wav").toExternalForm()
        );

        URL musica = getClass().getResource("/sonidos/bienvenida.mp3");

        tirarCarta = new AudioClip(getClass().getResource("/sonidos/cardThrown.wav").toExternalForm());

        musicaGanador = new AudioClip(getClass().getResource("/sonidos/win.wav").toExternalForm());

        musicaPerdedor = new AudioClip(getClass().getResource("/sonidos/lose.wav").toExternalForm());

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
    
    public void reproducirTirarCarta() {tirarCarta.play();}

    public void reproducirGanador() {musicaGanador.play();}

    public void reproducirPerdedor() {musicaPerdedor.play();}
    public void pausarMusica() {
        musicaFondo.pause();
    }
}