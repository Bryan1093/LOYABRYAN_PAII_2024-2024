package modelos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOverManager {
    public static void dibujarGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT, 36));
        g.drawString("GAME OVER", 300, 300);
    }
}