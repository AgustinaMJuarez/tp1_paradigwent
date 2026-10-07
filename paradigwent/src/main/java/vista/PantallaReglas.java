package vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaReglas {

    private final Stage stage;

    public PantallaReglas(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {

        BorderPane root = new BorderPane();

        root.getStyleClass().add("pantalla-reglas");

        VBox contenido = crearContenido();

        root.setCenter(contenido);

        Scene scene = new Scene(
                root,
                1000,
                700
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/styles.css")
                        .toExternalForm()
        );

        stage.setScene(scene);
    }

    private VBox crearContenido() {

        Label titulo = new Label("REGLAS");
        titulo.getStyleClass().add("titulo-reglas");

        Label objetivoTitulo = new Label("OBJETIVO");
        objetivoTitulo.getStyleClass().add("subtitulo-reglas");

        Label objetivo = new Label(
                "Ganá la partida siendo el último jugador con vidas restantes."
        );
        objetivo.getStyleClass().add("texto-reglas");

        Label comoJugarTitulo = new Label("CÓMO SE JUEGA");
        comoJugarTitulo.getStyleClass().add("subtitulo-reglas");

        Label comoJugar = new Label(
                "— Cada jugador comienza con 10 cartas.\n" +
                        "— Cada jugador comienza con 3 vidas.\n" +
                        "— Los jugadores se turnan para jugar cartas.\n" +
                        "— En cada turno se puede jugar una carta o pasar."
        );
        comoJugar.getStyleClass().add("texto-reglas");

        Label cartasTitulo = new Label("CARTAS");
        cartasTitulo.getStyleClass().add("subtitulo-reglas");

        Label cartas = new Label(
                "— Las criaturas se colocan en una de las tres líneas.\n" +
                        "— Las cartas de clima afectan el tablero.\n" +
                        "— Las cartas de efecto modifican el estado del juego."
        );
        cartas.getStyleClass().add("texto-reglas");

        Label rondasTitulo = new Label("RONDAS");
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
                event -> volverAlMenu()
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