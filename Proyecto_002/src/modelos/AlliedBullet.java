/*
 * Autor: Bryan
 * Versión: 1.0
 */

package modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Interface.IDrawable;
import Interface.IMove;

public class AlliedBullet implements IDrawable, IMove {
    private int x, y;           // Posición de la bala en el eje x e y
    private int width, high;    // Ancho y alto de la bala
    private Color color;        // Color de la bala
    private int speedY = 5; // Velocidad vertical de la bala (negativa para mover hacia arriba)

    /**
     * Constructor de la clase BalaAliada.
     * @param x Posición inicial en el eje x.
     * @param y Posición inicial en el eje y.
     * @param width Ancho de la bala.
     * @param high Alto de la bala.
     * @param color Color de la bala.
     */
    public AlliedBullet(int x, int y, int width, int high, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        this.color = color;
    }

    /**
     * Método para mover la bala hacia arriba.
     */
    public void move() {
        y -= speedY; // Mueve la bala hacia arriba (debido a la velocidad negativa)
    }

    /**
     * Método para obtener la posición en y de la bala.
     * @return Posición en y.
     */
    public int getY() {
        return y;
    }

    /**
     * Método para dibujar la bala en el panel.
     * @param g Objeto Graphics para dibujar.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, high); // Dibuja la bala como un rectángulo relleno en la posición actual
    }
    
    /**
     * Método para obtener la posición en x de la bala.
     * @return Posición en x.
     */
    public int getX() {
        return x;
    }

    /**
     * Método para obtener el ancho de la bala.
     * @return Ancho de la bala.
     */
    public int getAncho() {
        return width;
    }

    /**
     * Método para obtener el alto de la bala.
     * @return Alto de la bala.
     */
    public int getAlto() {
        return high;
    }
    
    /**
     * Método para verificar si la bala intersecta con una nave enemiga.
     * @param nave Nave enemiga con la que se quiere verificar la intersección.
     * @return true si hay intersección, false de lo contrario.
     */
    public boolean intersecta(EnemyShip nave) {
        Rectangle balaRect = new Rectangle(x, y, width, high);
        Rectangle naveRect = new Rectangle(nave.getX(), nave.getY(), nave.getAncho(), nave.getAlto());
        return balaRect.intersects(naveRect);
    }

}