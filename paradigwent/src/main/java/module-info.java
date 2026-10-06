module org.example {
    requires javafx.controls;
    requires com.google.gson;

    exports org.example;

    opens modelo.dto to com.google.gson;
    opens modelo.cartas to com.google.gson;

    exports modelo;
    exports modelo.cartas;
    exports modelo.loader;
    exports vista to javafx.graphics;
}
