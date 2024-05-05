package modelos;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {
    private int x, y; // Posición de la barra de vida
    private int width, height; // Ancho y alto de la barra de vida
    private int maxHealth; // Vida máxima
    private int currentHealth; // Vida actual

    public HealthBar(int x, int y, int width, int height, int maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void draw(Graphics g) {
        // Dibujar el contorno de la barra de vida
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        // Calcular el ancho de la barra de vida según la vida actual
        int barWidth = (int) (((double) currentHealth / maxHealth) * width);

        // Dibujar la barra de vida
        g.setColor(Color.GREEN);
        g.fillRect(x, y, barWidth, height);
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
}
