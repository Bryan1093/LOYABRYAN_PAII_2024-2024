/*
 * Autor: Bryan
 * Versión: 1.0
 */

package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Interface.IDrawable;
import Interface.IDead;

public class AlliedShip implements IDrawable, IDead {
    private int x, y;                           // Posición de la nave enemiga en el eje x e y
    private int width, high;                    // Ancho y alto de la nave enemiga
    private Color color;                        // Color de la nave enemiga
    private int naveX, naveY;                   // Posición de la nave aliada en el eje x e y
    private int moveAmount = 10;                // Cantidad de píxeles a mover en cada tecla presionada
    private JPanel panel;                       // Panel donde se dibuja la nave aliada
    private ArrayList<AlliedBullet> balas;        // Lista de balas disparadas por la nave aliada

    /**
     * Constructor de la clase NaveAliada.
     * @param x Posición inicial en el eje x.
     * @param y Posición inicial en el eje y.
     * @param width Ancho de la nave.
     * @param high Alto de la nave.
     * @param color Color de la nave.
     * @param panel Panel donde se dibuja la nave.
     */
    public AlliedShip(int x, int y, int width, int high, Color color, JPanel panel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        this.color = color;	
        this.panel = panel;
        this.naveX = 350;   // Posición inicial x de la nave aliada
        this.naveY = 450;   // Posición inicial y de la nave aliada       
        this.balas = new ArrayList<>();
    }

    /**
     * Método para dibujar la nave aliada en el panel.
     */
    @Override
    public void draw(Graphics g) {
        // Dibujar la nave aliada como un triángulo relleno
        int[] xPoints = {naveX, naveX + width / 2, naveX + width};
        int[] yPoints = {naveY + high, naveY, naveY + high};
        Polygon triangulo = new Polygon(xPoints, yPoints, 3);
        g.setColor(Color.WHITE);
        g.fillPolygon(triangulo);
        
        // Dibujar las balas disparadas por la nave aliada
        for (AlliedBullet bala : balas) {
            bala.draw(g);
        }
    }

    /**
     * Método para manejar el evento de tecla presionada.
     * @param e Evento de tecla.
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            naveX -= moveAmount;
            if (naveX < 0) {
                naveX = 0;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            naveX += moveAmount;
            if (naveX + width > panel.getWidth()) {
                naveX = panel.getWidth() - width;
            }
        } else if (key == KeyEvent.VK_SPACE) {
            // Disparar una nueva bala
            AlliedBullet bala = new AlliedBullet(naveX + width / 2, naveY, 5, 10, Color.WHITE);
            balas.add(bala);
        }
    }

    /**
     * Método para manejar el evento de tecla liberada.
     * @param e Evento de tecla.
     */
    public void keyReleased(KeyEvent e) {
        // Método no utilizado en esta versión
    }

    /**
     * Método para obtener la posición en x de la nave aliada.
     * @return Posición en x.
     */
    public int getX() {
        return naveX;
    }

    /**
     * Método para obtener la posición en y de la nave aliada.
     * @return Posición en y.
     */
    public int getY() {
        return naveY;
    }

    /**
     * Método para obtener el ancho de la nave aliada.
     * @return Ancho de la nave.
     */
    public int getAncho() {
        return width;
    }

    /**
     * Método para obtener el alto de la nave aliada.
     * @return Alto de la nave.
     */
    public int getAlto() {
        return high;
    }
    
    /**
     * Método para manejar la muerte de la nave aliada.
     */
    @Override
    public void dead() {
        // Lógica para cuando la nave aliada muere (por ejemplo, reiniciar posición)
        naveX = 350;
        naveY = 450;
    }
    
    /**
     * Método para obtener la lista de balas disparadas por la nave aliada.
     * @return Lista de balas.
     */
    public ArrayList<AlliedBullet> getBalas() {
        return balas;
    }
    
    /**
     * Método para mover las balas disparadas por la nave aliada.
     */
    public void moverBalas() {
        // Mover cada bala y eliminarla si sale de la pantalla
        for (int i = 0; i < balas.size(); i++) {
            AlliedBullet bala = balas.get(i);
            bala.move();
            if (bala.getY() < 0) {
                balas.remove(i);
                i--; // Disminuir el índice para compensar la eliminación del elemento
            }
        }
    }
}
