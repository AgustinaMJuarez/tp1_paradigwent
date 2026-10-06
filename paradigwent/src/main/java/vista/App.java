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


        Label titulo =
                new Label("PARADIGWENT");

        Label subtitulo =
                new Label(
                        "Paradigmas de Programación - FIUBA"
                );


        Button botonJugar =
                new Button("Jugar");

        Button botonReglas =
                new Button("Reglas");

        Button botonSalir =
                new Button("Salir");

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

        StackPane root =
                new StackPane();

        root.getChildren().addAll(
                background,
                menu
        );

        Scene scene =
                new Scene(
                        root,
                        1000,
                        700
                );

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/styles/styles.css"
                        )
                        .toExternalForm()
        );


        botonSalir.setOnAction(
                event -> stage.close()
        );


        stage.setTitle(
                "Paradigwent"
        );

        stage.setScene(
                scene
        );

        stage.show();
    }
}