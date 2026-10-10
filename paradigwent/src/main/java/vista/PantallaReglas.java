package vista;

import audio.GestorSonido;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class PantallaReglas {

    private static final String TEXTO_REGLAS = "REGLAS";
    private final Stage stage;
    private final GestorSonido sonido;

    public PantallaReglas(Stage stage, GestorSonido sonido) {
        this.stage = stage;
        this.sonido = sonido;
    }

    public void mostrar() {

        Image imagen = new Image(
                getClass().getResourceAsStream("/imgs/fondo.png")
        );

        ImageView background = new ImageView(imagen);

        background.setFitWidth(1000);
        background.setFitHeight(700);
        background.setPreserveRatio(false);

        VBox contenido = crearContenido();

        StackPane root = new StackPane(
                background,
                contenido
        );

        Scene scene = new Scene(
                root,
                1000,
                700
        );

        scene.getStylesheets().addAll(
                getClass().getResource("/styles/styles.css").toExternalForm(),
                getClass().getResource("/styles/reglas.css").toExternalForm()
        );

        stage.setScene(scene);

        root.setOpacity(0);

        FadeTransition entrada = new FadeTransition(
                Duration.millis(500),
                root
        );

        entrada.setFromValue(0);
        entrada.setToValue(1);

        entrada.play();
    }

    private VBox crearContenido() {

        Label titulo = new Label(TEXTO_REGLAS);
        titulo.getStyleClass().add("titulo-reglas");

        Label objetivoTitulo = new Label("✦ OBJETIVO ✦");
        objetivoTitulo.getStyleClass().add("subtitulo-reglas");

        Label objetivo = new Label(
                "Ganá la partida siendo el último jugador con vidas restantes."
        );
        objetivo.getStyleClass().add("texto-reglas");

        Label comoJugarTitulo = new Label("✦ CÓMO SE JUEGA ✦");
        comoJugarTitulo.getStyleClass().add("subtitulo-reglas");

        Label comoJugar = new Label(
                "— Cada jugador comienza con 10 cartas.\n" +
                        "— Cada jugador comienza con 2 vidas.\n" +
                        "— Los jugadores se turnan para jugar cartas.\n" +
                        "— En cada turno se puede jugar una carta o pasar."
        );
        comoJugar.getStyleClass().add("texto-reglas");

        Label cartasTitulo = new Label("✦ CARTAS ✦");
        cartasTitulo.getStyleClass().add("subtitulo-reglas");

        Label cartas = new Label(
                "— Las criaturas se colocan en una de las tres líneas.\n" +
                        "— Las cartas de clima afectan el tablero.\n" +
                        "— Las cartas de efecto modifican el estado del juego."
        );
        cartas.getStyleClass().add("texto-reglas");

        Label rondasTitulo = new Label("✦ RONDAS ✦");
        rondasTitulo.getStyleClass().add("subtitulo-reglas");

        Label rondas = new Label(
                "— Cuando ambos jugadores pasan, termina la ronda.\n" +
                        "— Gana la ronda quien tenga mayor fuerza.\n" +
                        "— El jugador que pierde la ronda pierde una vida.\n" +
                        "— En caso de empate, ambos jugadores pierden una vida."
        );
        rondas.getStyleClass().add("texto-reglas");

        Button volver = new Button("VOLVER");
        volver.getStyleClass().add("boton-reglas");

        volver.setOnAction(
                event -> {
                    volverAlMenu();
                    sonido.reproducirClick();
                }
        );

        VBox contenido = new VBox(
                18,
                titulo,
                objetivoTitulo,
                objetivo,
                comoJugarTitulo,
                comoJugar,
                cartasTitulo,
                cartas,
                rondasTitulo,
                rondas,
                volver
        );

        contenido.setAlignment(Pos.CENTER);
        contenido.getStyleClass().add("contenido-reglas");

        return contenido;
    }

    private void volverAlMenu() {
        App app = new App();
        app.start(stage);
    }
}