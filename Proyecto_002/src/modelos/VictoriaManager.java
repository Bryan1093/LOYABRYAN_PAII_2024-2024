
package modelos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class VictoriaManager {
    public static void dibujarVictoria(Graphics g, int width, int height) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.RED);
        g.drawString("YOU WIN!!", width / 2 - 50, height / 2);
    }
}