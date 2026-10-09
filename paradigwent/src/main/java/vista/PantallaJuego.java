package vista;

import audio.GestorSonido;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import modelo.*;
import modelo.cartas.*;

public class PantallaJuego {

    private final Stage stage;
    private final Partida partida;
    private final Jugador jugadorVista;
    private final GestorSonido sonidos;

    private Label vidasJugadorLabel;
    private Label vidasEnemigoLabel;
    private Label rondaLabel;
    private Label fuerzaJugadorLabel;
    private Label fuerzaEnemigoLabel;
    private Label turnoLabel;
    private Label climaLabel;

    private HBox mano;
    private Button pasar;

    private HBox asedioEnemigo;
    private HBox distanciaEnemigo;
    private HBox cuerpoEnemigo;

    private HBox asedioJugador;
    private HBox distanciaJugador;
    private HBox cuerpoJugador;

    public PantallaJuego(Stage stage, Partida partida, Jugador jugador, GestorSonido sonidos) {
        this.stage = stage;
        this.partida = partida;
        this.jugadorVista = jugador;
        this.sonidos = sonidos;
    }

    public void mostrar() {
        BorderPane tablero = crearTablero();

        Scene scene = new Scene(
                tablero,
                1000,
                700
        );

        scene.getStylesheets().addAll(
                getClass().getResource("/styles/styles.css").toExternalForm(),
                getClass().getResource("/styles/juego.css").toExternalForm(),
                getClass().getResource("/styles/fin.css").toExternalForm(),
                getClass().getResource("/styles/facciones.css").toExternalForm()

        );

        stage.setScene(scene);
        actualizarVista();
        manejarTurnoEnemigo();
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
        enemigo.getStyleClass().addAll(
                "informacion-jugador",
                claseFaccion(partida.getEnemigo().getFaccion())
        );

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
        jugador.getStyleClass().addAll(
                "informacion-jugador",
                claseFaccion(jugadorVista.getFaccion())
        );

        return jugador;
    }

    private VBox crearMazoYDescarte() {

        Label mazo = new Label("MAZO");
        Label descarte = new Label("DESCARTE");

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
        turnoLabel = new Label();
        turnoLabel.getStyleClass().add("turno");
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
                turnoLabel,
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

        climaLabel = new Label("Sin clima");
        climaLabel.getStyleClass().add("clima");

        VBox zona = new VBox(
                2,
                titulo,
                climaLabel
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

        String texto = carta.getNombre();

        if (carta instanceof Criatura criatura) {
            texto += "\n" + criatura.getFuerza();
        }

        Button nombreCarta = new Button(texto);
        nombreCarta.getStyleClass().addAll(
                "carta",
                claseFaccion(jugadorVista.getFaccion())
        );

        nombreCarta.setOnAction(event -> {
            partida.jugarCarta(carta);
            actualizarJuego();
        });

        return nombreCarta;
    }

    private HBox crearControles() {

        pasar = new Button("PASAR");
        Button rendirse = new Button("RENDIRSE");

        pasar.getStyleClass().add("boton-juego");
        rendirse.getStyleClass().add("boton-juego");

        pasar.setOnAction(event -> {
            sonidos.reproducirClick();
            partida.pasarTurno();
            actualizarJuego();
        });


        rendirse.setOnAction(event -> {
            sonidos.reproducirClick();
            jugadorVista.rendirse();
            actualizarJuego();
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

        boolean turnoJugador =
                partida.getJugadorActual() == jugadorVista;

        for (Carta carta : jugadorVista.getMano()) {

            Button botonCarta = crearCarta(carta);

            botonCarta.setDisable(!turnoJugador);

            mano.getChildren().add(botonCarta);
        }
    }

    private String corazones(int vidas) {
        return "♥ ".repeat(vidas);
    }

    private String claseFaccion(Faccion faccion) {
        return "faccion-" + faccion.name().toLowerCase();
    }

    private void actualizarVista() {
        actualizarMano();
        actualizarLineas();
        vidasJugadorLabel.setText(
                corazones(jugadorVista.getVidas())
        );
        vidasEnemigoLabel.setText(
                corazones(partida.getEnemigo().getVidas())
        );
//        climaLabel.setText();
        rondaLabel.setText("RONDA " + partida.getNumeroRonda());
        fuerzaJugadorLabel.setText(
                "Fuerza: " + jugadorVista.calcularFuerza(partida.getTablero())
        );

        fuerzaEnemigoLabel.setText(
                "Fuerza: " + partida.getEnemigo().calcularFuerza(partida.getTablero())
        );
        if (partida.getJugadorActual() == jugadorVista) {
            turnoLabel.setText("TU TURNO");
        } else {
            turnoLabel.setText("TURNO DEL ENEMIGO");
        }
        boolean turnoJugador =
                partida.getJugadorActual() == jugadorVista;

        pasar.setDisable(!turnoJugador);
    }

    private void manejarTurnoEnemigo() {
        if (partida.estaTerminada()) {
            mostrarFinDePartida();
            return;
        }

        if (partida.getJugadorActual() != jugadorVista) {
            PauseTransition pausa =
                    new PauseTransition(Duration.seconds(1));

            pausa.setOnFinished(event -> {
                partida.ejecutarTurnoEnemigo();
                actualizarVista();
                manejarTurnoEnemigo();
            });

            pausa.play();
        }
    }

    private void actualizarLinea(
            HBox linea,
            Jugador jugador,
            TipoAtaque tipoAtaque) {

        Label etiqueta = (Label) linea.getChildren().get(0);

        linea.getChildren().clear();
        linea.getChildren().add(etiqueta);

        for (Criatura criatura : jugador.getCriaturasEnLinea(tipoAtaque)) {

            String texto = criatura.getNombre()
                    + "\n"
                    + criatura.getFuerza();

            Label carta = new Label(texto);
            carta.getStyleClass().addAll(
                    "criatura",
                    claseFaccion(jugador.getFaccion())
            );

            linea.getChildren().add(carta);
        }
    }

    private void actualizarLineas() {
        actualizarLinea(
                cuerpoJugador,
                jugadorVista,
                TipoAtaque.CUERPO_A_CUERPO
        );

        actualizarLinea(
                distanciaJugador,
                jugadorVista,
                TipoAtaque.DISTANCIA
        );

        actualizarLinea(
                asedioJugador,
                jugadorVista,
                TipoAtaque.ASEDIO
        );

        actualizarLinea(
                cuerpoEnemigo,
                partida.getEnemigo(),
                TipoAtaque.CUERPO_A_CUERPO
        );

        actualizarLinea(
                distanciaEnemigo,
                partida.getEnemigo(),
                TipoAtaque.DISTANCIA
        );

        actualizarLinea(
                asedioEnemigo,
                partida.getEnemigo(),
                TipoAtaque.ASEDIO
        );
    }

    private void actualizarJuego() {
        actualizarVista();

        if (partida.estaTerminada()) {
            mostrarFinDePartida();
            return;
        }

        manejarTurnoEnemigo();
    }

    private void mostrarFinDePartida() {
        Jugador ganador = partida.ganador();

        boolean gano = ganador == jugadorVista;
        boolean empate = ganador == null;

        String titulo;
        String mensaje;

        if (gano) {
            titulo = "¡GANASTE!";
            mensaje = "Fuiste el último jugador con vidas restantes.";
        } else if (empate) {
            titulo = "¡EMPATE!";
            mensaje = "Ambos jugadores se quedaron sin vidas.";
        } else {
            titulo = "¡GANÓ EL ENEMIGO!";
            mensaje = "El enemigo se quedó con la partida. ¡Probá de nuevo!";
        }

        Label resultado = new Label(titulo);
        resultado.getStyleClass().addAll(
                "resultado",
                gano ? "resultado-victoria" :
                        empate ? "resultado-empate" : "resultado-derrota"
        );

        Label mensajeLabel = new Label(mensaje);
        mensajeLabel.getStyleClass().add("mensaje-final");

        Button volver = new Button("VOLVER AL MENÚ");
        volver.getStyleClass().addAll("boton-menu", "boton-primario");
        volver.setOnAction(event -> {
            sonidos.reproducirClick();
            new App().start(stage);
        });

        Button salir = new Button("SALIR");
        salir.getStyleClass().add("boton-menu");
        salir.setOnAction(event -> {
            sonidos.reproducirMusica();
            stage.close();
        });

        VBox panel = new VBox(20, resultado, mensajeLabel, volver, salir);
        panel.setAlignment(Pos.CENTER);
        panel.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        panel.getStyleClass().add("panel-final");

        StackPane root = new StackPane(panel);
        root.getStyleClass().add("pantalla-fin");

        Scene escenaFinal = new Scene(root, 1000, 700);
        escenaFinal.getStylesheets().addAll(
                getClass().getResource("/styles/styles.css").toExternalForm(),
                getClass().getResource("/styles/fin.css").toExternalForm()
        );

        stage.setScene(escenaFinal);
    }

}
