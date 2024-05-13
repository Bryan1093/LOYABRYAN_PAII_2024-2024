/*
 * Autor: Bryan
 * Versión: 1.0
 */

package JFrame;

import javax.swing.JFrame;

import controller.Contenedor;

/**
 * Clase principal que inicia la aplicación del juego Galaga chafa.
 */
public class MainGalaga {
    public static void main(String[] args) {
        // Crear una nueva ventana
        JFrame ventana = new JFrame("Galaga chafa");

        // Crear un nuevo juego y añadirlo a la ventana
        Contenedor juego = new Contenedor();
        ventana.add(juego);

        // Configurar el tamaño de la ventana
        ventana.setSize(800, 600);

        // Configurar el cierre de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}