package vista;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        /*
         * =============================
         * IMAGEN DE FONDO
         * =============================
         */

        Image imagen = new Image(
                getClass().getResourceAsStream(
                        "/imgs/bienvenida.png"
                )
        );

        ImageView background =
                new ImageView(imagen);

        background.setFitWidth(1000);
        background.setFitHeight(700);
        background.setPreserveRatio(false);


        /*
         * =============================
         * TITULOS
         * =============================
         */

        Label titulo =
                new Label("PARADIGWENT");

        Label subtitulo =
                new Label(
                        "Paradigmas de Programación - FIUBA"
                );


        /*
         * =============================
         * BOTONES
         * =============================
         */

        Button botonJugar =
                new Button("Jugar");

        Button botonReglas =
                new Button("Reglas");

        Button botonSalir =
                new Button("Salir");


        /*
         * =============================
         * CLASES CSS
         * =============================
         */

        titulo.getStyleClass().add(
                "titulo"
        );

        subtitulo.getStyleClass().add(
                "subtitulo"
        );

        botonJugar.getStyleClass().add(
                "boton-menu"
        );

        botonReglas.getStyleClass().add(
                "boton-menu"
        );

        botonSalir.getStyleClass().add(
                "boton-menu"
        );


        /*
         * =============================
         * MENU
         * =============================
         */

        VBox menu =
                new VBox(
                        20,
                        titulo,
                        subtitulo,
                        botonJugar,
                        botonReglas,
                        botonSalir
                );

        menu.setAlignment(
                Pos.CENTER
        );


        /*
         * =============================
         * CONTENEDOR PRINCIPAL
         * =============================
         *
         * StackPane permite poner
         * elementos uno encima del otro.
         *
         * Primero el fondo,
         * después el menú.
         */

        StackPane root =
                new StackPane();

        root.getChildren().addAll(
                background,
                menu
        );


        /*
         * =============================
         * ESCENA
         * =============================
         */

        Scene scene =
                new Scene(
                        root,
                        1000,
                        700
                );


        /*
         * =============================
         * CARGAR CSS
         * =============================
         */

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/styles/styles.css"
                        )
                        .toExternalForm()
        );


        /*
         * =============================
         * EVENTOS
         * =============================
         */

        botonSalir.setOnAction(
                event -> stage.close()
        );


        /*
         * =============================
         * MOSTRAR VENTANA
         * =============================
         */

        stage.setTitle(
                "Paradigwent"
        );

        stage.setScene(
                scene
        );

        stage.show();
    }
}