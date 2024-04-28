package Figuras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel implements ActionListener {
    private float opacity;
    private Timer timer;

    public GameOverScreen() {
        setPreferredSize(new Dimension(800, 600));
        opacity = 0;
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Dibujar un rectángulo semitransparente sobre toda la pantalla
        g2d.setColor(new Color(0, 0, 0, (int) (opacity * 255)));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar el mensaje "Game Over" en el centro de la pantalla
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        String message = "Game Over";
        int x = (getWidth() - fm.stringWidth(message)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(message, x, y);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Incrementar la opacidad gradualmente hasta llegar a 1
        opacity += 0.01f;
        if (opacity >= 1) {
            opacity = 1;
            timer.stop(); // Detener el temporizador cuando se alcanza la opacidad máxima
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Over Screen");
        GameOverScreen gameOverScreen = new GameOverScreen();
        frame.add(gameOverScreen);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }
}
