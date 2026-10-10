<div align="center">

# TP1: PARADIGWENT

### Universidad de Buenos Aires · Facultad de Ingeniería

**Materia:** Paradigmas de Programación
**Cuatrimestre:** 2.º Cuatrimestre 2026

---

## Docentes y corrector

* **Docentes:** Diego Essaya, Santiago Maraggi
* **Corrector:** Diego Essaya

## Integrantes

* **Felicitas Mannarino**
  Padrón: 114388

* **Agustina Milagros Juarez**
  Padrón: 114549

---

## Descripción del proyecto

Paradigwent es un juego de cartas por turnos desarrollado en Java como trabajo práctico de la materia Paradigmas de Programación. El juego permite enfrentarse a un oponente controlado por la computadora, utilizando cartas de criaturas, efectos y clima.

El objetivo es ganar las rondas mediante el uso estratégico de las cartas y conservar vidas hasta derrotar al oponente. El proyecto utiliza JavaFX para la interfaz gráfica y Maven para la gestión y construcción del proyecto.

## Instrucciones de ejecución

### Requisitos previos

* Tener instalado un JDK compatible con la versión de Java configurada en el proyecto.
* Tener instalado Maven, o utilizar el Maven Wrapper si está incluido en el repositorio.

### Ejecución

1. Clonar el repositorio del proyecto.
2. Abrir una terminal en el directorio raíz del proyecto.
3. Ejecutar el comando de Maven correspondiente para iniciar la aplicación.

```bash
mvn javafx:run
```

## Instrucciones de juego

### Objetivo

Derrotar al oponente conservando al menos una vida cuando el rival se quede sin vidas.

### Reglas

* Cada jugador comienza con 2 vidas.
* Cada jugador recibe una mano inicial de 10 cartas.
* Los jugadores se turnan para jugar cartas o pasar.
* Las cartas de criatura se colocan en una de las líneas de combate: cuerpo a cuerpo, distancia o asedio.
* Las cartas de clima afectan a las criaturas de la línea correspondiente (Escarcha/Helada establece la fuerza de todas las cartas de Combate Cuerpo a Cuerpo en 1 para ambos jugadores, niebla establece la fuerza de todas las cartas de Combate a Distancia en 1 para ambos jugadores y lluvias establece la fuerza de todas las cartas de Combate de Asedio en 1 para ambos jugadores).
* Las cartas de efecto permiten modificar el desarrollo de la ronda según su habilidad (las de cuerno de comandante duplican la fuerza de todas las cartas que esten en la fila en la que se decia colocarla y las de quemadura destruyen la carta con fuerza mas alta que este en el tablero sin importar de que jugador sea).
* Pasar finaliza el turno del jugador durante esa ronda.
* Cuando ambos jugadores pasan, la ronda termina y se determina quién la ganó.
* El jugador que pierde una ronda pierde una vida. En caso de empate, ambos jugadores pierden una vida.
* Las cartas utilizadas se descartan al finalizar la ronda.
* La partida termina cuando un jugador se queda sin vidas.

  
## Formato JSON para la carga de mazos

El proyecto utiliza archivos en formato JSON para definir los mazos de cartas. Estos archivos se encuentran en el directorio de recursos `src/main/modelos/loader/`.

La carga se realiza mediante la clase `CargadorMazo`, que utiliza la biblioteca Gson para convertir el contenido del archivo JSON en un objeto `MazoDTO`. Luego, se recorren las cartas definidas en el mazo y se utiliza `CargadorCartas` para crear los objetos correspondientes del modelo.

La implementación permite cargar cartas de distintos tipos:

* **CRIATURA:** contiene información como el nombre, la fuerza y el tipo de ataque. Opcionalmente, puede tener una habilidad.
* **CLIMA:** contiene el nombre y el tipo de clima.
* **EFECTO:** contiene el nombre y la habilidad asociada.

Las habilidades disponibles son `QUEMADURA` y `CUERNO_DE_COMANDANTE`.

### Estructura del archivo JSON

Cada archivo de mazo debe contener la facción y una lista de cartas. Cada carta incluye los campos correspondientes a su tipo.

Ejemplo ilustrativo:

```json
{
  "faccion": "BOSQUE",
  "cartas": [
    {
      "tipo": "CRIATURA",
      "nombre": "Guerrero del bosque",
      "fuerza": 5,
      "tipoAtaque": "CUERPO_A_CUERPO"
    },
    {
      "tipo": "CLIMA",
      "nombre": "Escarcha",
      "tipoClima": "ESCARCHA"
    },
    {
      "tipo": "EFECTO",
      "nombre": "Quemadura",
      "tipoEfecto": "QUEMADURA"
    }
  ]
}
```

El ejemplo es ilustrativo: los nombres de los campos y los valores admitidos deben coincidir con los definidos en las clases `MazoDTO` y `CartaDTO`, así como con los enumerados del proyecto.

### Proceso de carga

1. Se busca el archivo solicitado dentro de `/mazos/`.
2. Gson convierte el JSON en un objeto `MazoDTO`.
3. Se recorre la lista de cartas y se crean los objetos correspondientes según su tipo.
4. Se construye el objeto `Mazo` con la facción y las cartas cargadas.

Si no se encuentra el archivo o ocurre un error durante la carga, se genera una excepción.

</div>
