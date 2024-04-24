package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import Interface.IDibujable;

public class BalaEnemiga implements IDibujable {
    private int x, y;           // Posición de la bala en el eje x e y
    private int ancho, alto;    // Ancho y alto de la bala
    private Color color;        // Color de la bala
    private int velocidadY = 1; // Velocidad vertical de la bala

    public BalaEnemiga(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
    }
    
    public void mover() {
        y += velocidadY; // Mueve la bala hacia abajo
    }
    
    public int getY() {
        return y;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(x - ancho / 2, y - alto / 2, ancho, alto); // Dibuja la bala como un óvalo relleno en la posición actual
    }
}
