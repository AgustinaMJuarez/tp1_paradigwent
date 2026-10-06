package vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaJuego {

    private final Stage stage;

    public PantallaJuego(Stage stage) {
        this.stage = stage;
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

        Label vidas = new Label("♥ ♥ ♥");
        vidas.getStyleClass().add("vidas");

        Label puntaje = new Label("Puntaje: 0");
        puntaje.getStyleClass().add("puntaje");

        VBox mazoYDescarte = crearMazoYDescarte();

        VBox enemigo = new VBox(
                8,
                nombre,
                vidas,
                puntaje,
                mazoYDescarte
        );

        enemigo.setAlignment(Pos.CENTER);
        enemigo.getStyleClass().add("informacion-jugador");

        return enemigo;
    }

    private VBox crearInformacionJugador() {

        Label nombre = new Label("JUGADOR");
        nombre.getStyleClass().add("nombre-jugador");

        Label vidas = new Label("♥ ♥ ♥");
        vidas.getStyleClass().add("vidas");

        Label puntaje = new Label("Puntaje: 0");
        puntaje.getStyleClass().add("puntaje");

        VBox mazoYDescarte = crearMazoYDescarte();

        VBox jugador = new VBox(
                8,
                nombre,
                vidas,
                puntaje,
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

        HBox asedioEnemigo = crearLinea("ASEDIO");
        HBox distanciaEnemigo = crearLinea("DISTANCIA");
        HBox cuerpoEnemigo = crearLinea("CUERPO A CUERPO");

        VBox clima = crearZonaClima();

        HBox cuerpoJugador = crearLinea("CUERPO A CUERPO");
        HBox distanciaJugador = crearLinea("DISTANCIA");
        HBox asedioJugador = crearLinea("ASEDIO");

        VBox campo = new VBox(
                5,
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

        HBox mano = crearMano();
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

    private HBox crearMano() {

        HBox mano = new HBox(
                8,
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA"),
                crearCarta("CARTA")
        );

        mano.setAlignment(Pos.CENTER);
        mano.getStyleClass().add("mano");

        return mano;
    }

    private Button crearCarta(String nombre) {

        Button carta = new Button(nombre);

        carta.getStyleClass().add("carta");

        return carta;
    }

    private HBox crearControles() {

        Button pasar = new Button("PASAR");
        Button rendirse = new Button("RENDIRSE");

        pasar.getStyleClass().add("boton-juego");
        rendirse.getStyleClass().add("boton-juego");

        HBox controles = new HBox(
                15,
                pasar,
                rendirse
        );

        controles.setAlignment(Pos.CENTER);

        return controles;
    }
}
