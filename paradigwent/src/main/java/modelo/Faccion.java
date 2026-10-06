package modelo;

public enum Faccion {

    PLAYA("playa.json"),
    BOSQUE("bosque.json"),
    CONSTITUCION("constitucion.json");

    private final String archivo;

    Faccion(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }
}
