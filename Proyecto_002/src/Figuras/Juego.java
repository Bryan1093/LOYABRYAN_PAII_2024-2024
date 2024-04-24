package Figuras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase que representa el panel de juego donde se desarrolla la lógica del juego Galaga.
 */
public class Juego extends JPanel implements ActionListener, KeyListener {
    private NaveAliada naveAliada;
    private NaveEnemiga[] navesEnemigas;
    private NaveEnemiga[] navesEnemigasAbajo;
    private boolean gameOver;
    private Timer timer;

    /**
     * Constructor de la clase Juego. Inicializa los elementos del juego.
     */
    public Juego() {
        setBackground(Color.BLACK);
        naveAliada = new NaveAliada(250, 500, 60, 70, Color.WHITE, this);
        navesEnemigas = new NaveEnemiga[5];
        navesEnemigasAbajo = new NaveEnemiga[5];
        gameOver = false;

        // Posiciones iniciales de las naves enemigas
        for (int i = 0; i < navesEnemigas.length; i++) {
            navesEnemigas[i] = new NaveEnemiga(40 + i * 135, 50, 60, 60, Color.RED);
        }

        // Posiciones iniciales de las naves enemigas abajo
        for (int i = 0; i < navesEnemigasAbajo.length; i++) {
            navesEnemigasAbajo[i] = new NaveEnemiga(120 + i * 135, 200, 60, 60, Color.RED);
        }

        // Configurar temporizador para actualizar el juego
        timer = new Timer(1000 / 60, this); // Llamar al actionPerformed aproximadamente cada 60 veces por segundo
        timer.start();

        setFocusable(true); // Permitir que el panel tenga el foco
        addKeyListener(this); // Agregar KeyListener al panel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizar(); // Actualizar el estado del juego en cada iteración del temporizador
    }

    /**
     * Método para actualizar el estado del juego en cada iteración del temporizador.
     */
    public void actualizar() {
        for (NaveEnemiga naveEnemiga : navesEnemigas) {
            naveEnemiga.mover();
            if (naveEnemiga.getY() >= 400) { // 66% de la pantalla (600px)
                gameOver = true;
            }
        }

        for (NaveEnemiga naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.mover();
            if (naveEnemiga.getY() >= 400) { // 66% de la pantalla (600px)
                gameOver = true;
            }
        }

        repaint(); // Volver a dibujar el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        naveAliada.dibujar(g);

        for (NaveEnemiga naveEnemiga : navesEnemigas) {
            naveEnemiga.dibujar(g);
        }

        for (NaveEnemiga naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.dibujar(g);
        }

        if (gameOver) {
            g.setColor(Color.WHITE);
            timer.stop(); // Detener el temporizador
            JOptionPane.showMessageDialog(this, "GAME OVER"); // Mostrar mensaje de GAME OVER
            System.exit(0); // Cerrar la aplicación
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se implementa en este juego
    }

    @Override
    public void keyPressed(KeyEvent e) {
        naveAliada.keyPressed(e);
        repaint(); // Volver a dibujar la nave después de moverla
    }

    @Override
    public void keyReleased(KeyEvent e) {
        naveAliada.keyReleased(e);
        repaint(); // Volver a dibujar la nave después de moverla
    }

}
