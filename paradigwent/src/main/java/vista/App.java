package vista;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import modelo.*;
import modelo.loader.*;


public class App extends Application {

    private static final String TEXTO_JUGAR = "JUGAR";
    private static final String TEXTO_REGLAS = "REGLAS";
    private static final String TEXTO_SALIR = "SALIR";

    @Override
    public void start(Stage stage) {

        Font.loadFont(getClass().getResourceAsStream("/fonts/CinzelDecorative-Bold.ttf"),54);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Montserrat-Regular.ttf"),17);

        Image imagen = new Image(getClass().getResourceAsStream("/imgs/bienvenida.png"));
        ImageView background = new ImageView(imagen);

        background.setFitWidth(1000);
        background.setFitHeight(700);
        background.setPreserveRatio(false);

        Label titulo = new Label("PARADIGWENT");
        Label subtitulo = new Label("Paradigmas de Programación · FIUBA");

        Button botonJugar = new Button(TEXTO_JUGAR);
        Button botonReglas = new Button(TEXTO_REGLAS);
        Button botonSalir = new Button(TEXTO_SALIR);

        titulo.getStyleClass().add("titulo");
        subtitulo.getStyleClass().add("subtitulo");
        botonJugar.getStyleClass().add("boton-menu");
        botonReglas.getStyleClass().add("boton-menu");
        botonSalir.getStyleClass().add("boton-menu");

        VBox menu =
                new VBox(
                        18,
                        titulo,
                        subtitulo,
                        botonJugar,
                        botonReglas,
                        botonSalir
                );

        menu.setAlignment(Pos.CENTER);
        menu.getStyleClass().add("panel-menu");

        StackPane root = new StackPane();

        root.getChildren().addAll(background, menu);

        Scene scene = new Scene(root, 1000, 700);

        scene.getStylesheets().add(getClass().getResource("/styles/bienvenida.css").toExternalForm());

        FadeTransition fade = new FadeTransition(Duration.millis(900), menu);

        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        ScaleTransition backgroundZoom = new ScaleTransition(Duration.seconds(14), background);

        backgroundZoom.setFromX(1);
        backgroundZoom.setFromY(1);

        backgroundZoom.setToX(1.05);
        backgroundZoom.setToY(1.05);

        backgroundZoom.setAutoReverse(true);
        backgroundZoom.setCycleCount(Animation.INDEFINITE);
        backgroundZoom.play();

        agregarAnimacionHover(botonJugar);
        agregarAnimacionHover(botonReglas);
        agregarAnimacionHover(botonSalir);

        botonSalir.setOnAction(event -> stage.close());

        botonJugar.setOnAction(
                event -> iniciarJuego(stage)
        );

        botonReglas.setOnAction(
                event -> {
                    PantallaReglas pantallaReglas = new PantallaReglas(stage);
                    pantallaReglas.mostrar();
                }
        );

        stage.setTitle("Paradigwent");
        stage.setScene(scene);
        stage.show();
    }

    private void iniciarJuego(Stage stage) {

        CargadorCartas cargadorCartas = new CargadorCartasDefault();
        CargadorMazo cargadorMazo = new CargadorMazo(cargadorCartas);

        SorteadorFacciones sorteador = new SorteadorFacciones();

        Faccion[] facciones = sorteador.sortear();
        Faccion faccionJugador = facciones[0];
        Faccion faccionAutomata = facciones[1];

        Mazo mazoJugador =
                cargadorMazo.cargar(
                        faccionJugador.getArchivo()
                );

        Mazo mazoAutomata =
                cargadorMazo.cargar(
                        faccionAutomata.getArchivo()
                );

        mazoJugador.sortearMazo();
        mazoAutomata.sortearMazo();

        Jugador jugador =  new Jugador(mazoJugador);
        Jugador jugadorAutomata = new Jugador(mazoAutomata);

        jugador.repartirMano();
        jugadorAutomata.repartirMano();

        Partida partida =
                new Partida(
                        jugador,
                        jugadorAutomata
                );

        partida.iniciarPartida();

        PantallaJuego pantallaJuego =
                new PantallaJuego(
                        stage,
                        partida,
                        jugador
                );

        pantallaJuego.mostrar();
    }

    private void agregarAnimacionHover(Button boton) {
        boton.setOnMouseEntered(
                event -> {
                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(130),
                                    boton
                            );

                    scale.setToX(1.05);
                    scale.setToY(1.05);

                    scale.play();
                }
        );


        boton.setOnMouseExited(
                event -> {
                    ScaleTransition scale =
                            new ScaleTransition(
                                    Duration.millis(130),
                                    boton
                            );

                    scale.setToX(1);
                    scale.setToY(1);

                    scale.play();
                }
        );
    }
}