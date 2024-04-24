package Principal;

import javax.swing.JFrame;

import Figuras.Juego;

/**
 * Clase principal que inicia la aplicación del juego Galaga chafa.
 * Author: Bryan
 * Version: 1.0
 */
public class MainGalaga {
    public static void main(String[] args) {
        // Crear una nueva ventana
        JFrame ventana = new JFrame("Galaga chafa");

        // Crear un nuevo juego y añadirlo a la ventana
        Juego juego = new Juego();
        ventana.add(juego);

        // Configurar el tamaño de la ventana
        ventana.setSize(800, 600);

        // Configurar el cierre de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}