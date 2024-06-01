/*
 * Autor: Bryan
 * Versión: 1.0
 */

package modelos;

import java.awt.Color;
import java.awt.Graphics;


import Interface.IDrawable;
import controller.Contenedor;

public class EnemyBullet implements IDrawable {
    private int x, y;           // Posición de la bala en el eje x e y
    private int width, high;    // Ancho y alto de la bala
    private Color color;        // Color de la bala
    private int speedY = 1; // Velocidad vertical de la bala
    private Color bulletColor; // Define la variable para el color de la bala

    public EnemyBullet(int x, int y, int width, int high, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        this.color = color;
    }
    
    public int getY() {
        return y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getWidth() {
        return width;
    }

    /**
     * Método para obtener el alto de la bala.
     * @return Alto de la bala.
     */
    public int getHigh() {
        return high;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, high); // Dibuja la bala como un óvalo relleno en la posición actual
    }


    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void moverAbajo() {
        y += 10; // Move the bullet downwards
    }

}
