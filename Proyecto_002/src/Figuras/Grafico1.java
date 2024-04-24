package Figuras;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Grafico1 extends JFrame implements KeyListener {

    private JPanel contentPane;
    private int naveWidth = 60;
    private int naveHeight = 60;
    private int naveSpacing = 100;
    private int numNaves = 4;
    private int naveY = 50;
    private int naveX = 400; // Posición inicial de la nave en el centro
    private Timer timer;
    private boolean gameOver;
    private int moveAmount = 10; // Cantidad de píxeles a mover la nave

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Grafico1 frame = new Grafico1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Grafico1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintNaves(g);
                if (gameOver) {
                    g.setColor(Color.RED);
                    g.drawString("GAME OVER", 350, 30);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(0, 0, 800, 600);
        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    moveNaves();
                    checkGameOver();
                    repaint();
                }
            }
        });
        timer.start();
    }

    private void paintNaves(Graphics g) {
        // Dibujar nave (triángulo blanco)
        int[] vxNave = {naveX, naveX + 40, naveX + 80}; // Puntos x del triángulo
        int[] vyNave = {520, 450, 520}; // Puntos y del triángulo

        g.setColor(Color.white);
        g.fillPolygon(vxNave, vyNave, 3);

        // Dibujar naves arriba (cuadrados verdes)
        g.setColor(Color.green);
        for (int i = 0; i < numNaves; i++) {
            int x = 90 + i * (naveWidth + naveSpacing);
            g.fillRect(x, naveY, naveWidth, naveHeight);
        }

        // Dibujar naves abajo (cuadrados verdes)
        for (int i = 0; i < numNaves; i++) {
            int x = 180 + i * (naveWidth + naveSpacing);
            g.fillRect(x, naveY + 100, naveWidth, naveHeight);
        }
    }

    private void moveNaves() {
        naveY += 5; // Mover las naves hacia abajo
    }

    private void checkGameOver() {
        if (naveY >= 400) { // Si alguna nave llega al 66% de la pantalla
            gameOver = true;
            timer.stop(); // Detener el temporizador
            JOptionPane.showMessageDialog(this, "GAME OVER"); // Mostrar mensaje de GAME OVER
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            naveX -= moveAmount;
            if (naveX < 0) {
                naveX = 0;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            naveX += moveAmount;
            if (naveX + 80 > getWidth()) {
                naveX = getWidth() - 80;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}