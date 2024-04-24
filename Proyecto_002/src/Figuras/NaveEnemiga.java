package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Interface.IDibujable;

public class NaveEnemiga implements IDibujable {
    private int x, y;                   // Posición de la nave enemiga en el eje x e y
    private int ancho, alto;            // Ancho y alto de la nave enemiga
    private Color color;                // Color de la nave enemiga
    private BalaEnemiga balaEnemiga;    // Bala asociada a la nave enemiga

    public NaveEnemiga(int x, int y, int ancho, int alto, Color color) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
        // Crear una bala asociada a la nave enemiga
        this.balaEnemiga = new BalaEnemiga(x + ancho / 2, y + alto, 12, 27, Color.YELLOW);
    }

    @Override
    public void dibujar(Graphics g) {
        // Dibujar la nave enemiga como un polígono relleno
        int[] xPoints = {x, x + ancho / 2, x + ancho, x + ancho / 2};
        int[] yPoints = {y + alto, y, y + alto, y + alto / 2};
        Polygon nave = new Polygon(xPoints, yPoints, 4);
        g.setColor(Color.RED);
        g.fillPolygon(nave);

        // Dibujar la bala asociada a la nave enemiga
        balaEnemiga.dibujar(g);
    }
    
    public void mover() {
        y += 1; // Mover la nave hacia abajo
        balaEnemiga.mover(); // Mover la bala asociada a la nave enemiga
    }
    
    public int getY() {
        return y;
    }
}