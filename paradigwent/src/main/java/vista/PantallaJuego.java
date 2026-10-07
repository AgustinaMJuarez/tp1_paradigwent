package vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Partida;
import modelo.Jugador;
import modelo.cartas.Carta;

public class PantallaJuego {

    private final Stage stage;
    private final Partida partida;
    private final Jugador jugadorVista;

    private Label vidasJugadorLabel;
    private Label vidasEnemigoLabel;
    private Label rondaLabel;
    private Label fuerzaJugadorLabel;
    private Label fuerzaEnemigoLabel;

    private Label climaLabel;

    private HBox mano;

    private HBox asedioEnemigo;
    private HBox distanciaEnemigo;
    private HBox cuerpoEnemigo;

    private HBox asedioJugador;
    private HBox distanciaJugador;
    private HBox cuerpoJugador;

    public PantallaJuego(Stage stage, Partida partida, Jugador jugador) {
        this.stage = stage;
        this.partida = partida;
        this.jugadorVista = jugador;
    }

    public void mostrar() {
        BorderPane tablero = crearTablero();

        Scene scene = new Scene(
                tablero,
                1000,
                700
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/styles.css")
                        .toExternalForm()
        );

        stage.setScene(scene);
        actualizarVista();
    }

    private BorderPane crearTablero() {
        BorderPane tablero = new BorderPane();

        tablero.getStyleClass().add("tablero");
        tablero.setLeft(crearPanelJugadores());
        tablero.setCenter(crearCampoDeJuego());
        tablero.setBottom(crearZonaInferior());

        return tablero;
    }

    private VBox crearPanelJugadores() {

        VBox panel = new VBox(
                30,
                crearInformacionEnemigo(),
                crearInformacionJugador()
        );

        panel.setAlignment(Pos.CENTER);
        panel.getStyleClass().add("panel-jugadores");

        return panel;
    }

    private VBox crearInformacionEnemigo() {

        Label nombre = new Label("ENEMIGO");
        nombre.getStyleClass().add("nombre-jugador");

        vidasEnemigoLabel = new Label("♥ ♥ ♥");
        vidasEnemigoLabel.getStyleClass().add("vidas");

        fuerzaEnemigoLabel = new Label("Fuerza: 0");
        fuerzaEnemigoLabel.getStyleClass().add("puntaje");

        VBox mazoYDescarte = crearMazoYDescarte();

        VBox enemigo = new VBox(
                8,
                nombre,
                vidasEnemigoLabel,
                fuerzaEnemigoLabel,
                mazoYDescarte
        );

        enemigo.setAlignment(Pos.CENTER);
        enemigo.getStyleClass().add("informacion-jugador");

        return enemigo;
    }

    private VBox crearInformacionJugador() {

        Label nombre = new Label("JUGADOR");
        nombre.getStyleClass().add("nombre-jugador");

        vidasJugadorLabel = new Label("♥ ♥ ♥");
        vidasJugadorLabel.getStyleClass().add("vidas");

        fuerzaJugadorLabel = new Label("Fuerza: 0");
        fuerzaJugadorLabel.getStyleClass().add("puntaje");

        VBox mazoYDescarte = crearMazoYDescarte();

        VBox jugador = new VBox(
                8,
                nombre,
                vidasJugadorLabel,
                fuerzaJugadorLabel,
                mazoYDescarte
        );

        jugador.setAlignment(Pos.CENTER);
        jugador.getStyleClass().add("informacion-jugador");

        return jugador;
    }

    private VBox crearMazoYDescarte() {

        Button mazo = new Button("MAZO");
        Button descarte = new Button("DESCARTE");

        mazo.getStyleClass().add("pila");
        descarte.getStyleClass().add("pila");

        VBox contenedor = new VBox(
                8,
                mazo,
                descarte
        );

        contenedor.setAlignment(Pos.CENTER);
        return contenedor;
    }

    private VBox crearCampoDeJuego() {
        rondaLabel = new Label(
                "RONDA " + partida.getNumeroRonda()
        );

        rondaLabel.getStyleClass().add("ronda");

        asedioEnemigo = crearLinea("ASEDIO");
        distanciaEnemigo = crearLinea("DISTANCIA");
        cuerpoEnemigo = crearLinea("CUERPO A CUERPO");

        VBox clima = crearZonaClima();

        cuerpoJugador = crearLinea("CUERPO A CUERPO");
        distanciaJugador = crearLinea("DISTANCIA");
        asedioJugador = crearLinea("ASEDIO");

        VBox campo = new VBox(
                5,
                rondaLabel,
                asedioEnemigo,
                distanciaEnemigo,
                cuerpoEnemigo,
                clima,
                cuerpoJugador,
                distanciaJugador,
                asedioJugador
        );

        campo.setAlignment(Pos.CENTER);
        campo.getStyleClass().add("campo-juego");

        return campo;
    }

    private HBox crearLinea(String nombre) {

        Label etiqueta = new Label(nombre);
        etiqueta.getStyleClass().add("nombre-linea");

        HBox linea = new HBox(
                etiqueta
        );

        linea.setAlignment(Pos.CENTER_LEFT);
        linea.getStyleClass().add("linea");

        return linea;
    }

    private VBox crearZonaClima() {

        Label titulo = new Label("CLIMA");
        titulo.getStyleClass().add("titulo-clima");

        Label clima = new Label("Sin clima");
        clima.getStyleClass().add("clima");

        VBox zona = new VBox(
                2,
                titulo,
                clima
        );

        zona.setAlignment(Pos.CENTER);
        zona.getStyleClass().add("zona-clima");

        return zona;
    }

    private VBox crearZonaInferior() {

        crearMano();
        HBox controles = crearControles();

        VBox zona = new VBox(
                8,
                mano,
                controles
        );

        zona.setAlignment(Pos.CENTER);
        zona.getStyleClass().add("zona-inferior");

        return zona;
    }

    private void crearMano() {

        mano = new HBox(8);
        mano.setAlignment(Pos.CENTER);
        mano.getStyleClass().add("mano");

        actualizarMano();
    }

    private Button crearCarta(Carta carta) {

        Button nombreCarta = new Button(carta.getNombre());
        nombreCarta.getStyleClass().add("carta");

        nombreCarta.setOnAction(event -> {
            partida.jugarCarta(carta);
            actualizarVista();
        });
        return nombreCarta;
    }

    private HBox crearControles() {

        Button pasar = new Button("PASAR");
        Button rendirse = new Button("RENDIRSE");

        pasar.getStyleClass().add("boton-juego");
        rendirse.getStyleClass().add("boton-juego");

        pasar.setOnAction(event -> {
            partida.pasarTurno();
            actualizarVista();
        });


        rendirse.setOnAction(event -> {
            jugadorVista.rendirse();
            actualizarVista();
        });


        HBox controles = new HBox(
                15,
                pasar,
                rendirse
        );

        controles.setAlignment(Pos.CENTER);

        return controles;
    }

    private void actualizarMano() {
        mano.getChildren().clear();
        for (Carta carta : jugadorVista.getMano()) {

            Button botonCarta =
                    crearCarta(carta);

            mano.getChildren().add(
                    botonCarta
            );
        }
    }

    private String corazones(int vidas) {
        return "♥ ".repeat(vidas);
    }

    private void actualizarVista() {
        actualizarMano();
        vidasJugadorLabel.setText(
                corazones(jugadorVista.getVidas())
        );
        vidasEnemigoLabel.setText(
                corazones(partida.getEnemigo().getVidas())
        );
        rondaLabel.setText("RONDA " + partida.getNumeroRonda());
        fuerzaJugadorLabel.setText(
                "Fuerza: " + jugadorVista.calcularFuerza(partida.getTablero())
        );

        fuerzaEnemigoLabel.setText(
                "Fuerza: " + partida.getEnemigo().calcularFuerza(partida.getTablero())
        );
    }

}
