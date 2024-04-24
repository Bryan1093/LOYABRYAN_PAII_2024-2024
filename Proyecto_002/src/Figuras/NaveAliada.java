package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Interface.IDibujable;

public class NaveAliada implements IDibujable {
    private int x, y;                           // Posición de la nave enemiga en el eje x e y
    private int ancho, alto;                    // Ancho y alto de la nave enemiga
    private Color color;                        // Color de la nave enemiga
    private int naveX, naveY;                   // Posición de la nave aliada en el eje x e y
    private int moveAmount = 10;                // Cantidad de píxeles a mover en cada tecla presionada
    private JPanel panel;                       // Panel donde se dibuja la nave aliada

    public NaveAliada(int x, int y, int ancho, int alto, Color color, JPanel panel) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color;
        this.panel = panel;
        this.naveX = 350;   // Posición inicial x de la nave aliada
        this.naveY = 450;   // Posición inicial y de la nave aliada
    }

    @Override
    public void dibujar(Graphics g) {
        // Dibujar la nave aliada como un triángulo relleno
        int[] xPoints = {naveX, naveX + ancho / 2, naveX + ancho};
        int[] yPoints = {naveY + alto, naveY, naveY + alto};
        Polygon triangulo = new Polygon(xPoints, yPoints, 3);
        g.setColor(Color.WHITE);
        g.fillPolygon(triangulo);
    }

    public void mover() {
        // No hay movimiento automático de la nave aliada en esta versión
        // El movimiento se controla mediante las teclas izquierda y derecha
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            naveX -= moveAmount;
            if (naveX < 0) {
                naveX = 0;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            naveX += moveAmount;
            if (naveX + ancho > panel.getWidth()) {
                naveX = panel.getWidth() - ancho;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // Método no utilizado en esta versión
    }

    public int getX() {
        return naveX;
    }

    public int getY() {
        return naveY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
