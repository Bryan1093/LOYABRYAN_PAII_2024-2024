/**
 * Programa para dibujar 3 figuras geométricas
 * 
 * @author Bryan Loya
 * @version 1.0
 * @since 2024-04-17
 */

package default_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Renderer_Figuras extends JFrame {
    private enum Shape {TRIANGLE, SQUARE, CIRCLE}
    private Shape currentShape = Shape.TRIANGLE;

    public Renderer_Figuras() {
        setTitle("Dibujar Figuras");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear botones
        JButton triangleButton = new JButton("Triángulo");
        JButton squareButton = new JButton("Cuadrado");
        JButton circleButton = new JButton("Círculo");

        // Agregar ActionListener a cada botón
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = Shape.TRIANGLE;
                repaint(); // Volver a dibujar la ventana
            }
        });

        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = Shape.SQUARE;
                repaint(); // Volver a dibujar la ventana
            }
        });

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = Shape.CIRCLE;
                repaint(); // Volver a dibujar la ventana
            }
        });

     // Crear un panel para los botones y organizarlos verticalmente con espacios entre ellos
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(15)); // Espacio vertical entre los botones
        buttonPanel.add(triangleButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(squareButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(circleButton);

        // Crear un panel para las figuras y centrarlo horizontalmente
        JPanel shapePanel = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        	    super.paintComponent(g);
        	    Graphics2D g2d = (Graphics2D) g;
        	    
        	    switch (currentShape) {
        	    case TRIANGLE:
        	    	
        	    	 int widthT = getWidth();
             	     int heightT = getHeight();
             	     
        	        // Dibujar el triángulo
        	        int[] xPointsTriangle = {widthT / 2, (int) (widthT * 0.65), (int) (widthT * 0.35)};
        	        int[] yPointsTriangle = {(int) (heightT * 0.25), (int) (heightT * 0.75), (int) (heightT * 0.75)};
        	        int nPointsTriangle = 3;

        	        g2d.setColor(Color.RED);
        	        g2d.fillPolygon(xPointsTriangle, yPointsTriangle, nPointsTriangle);
        	        break;

        	    case SQUARE:
        	    	
        	    	 int widthS = getWidth();
             	     int heightS = getHeight();
             	     
        	        // Dibujar el cuadrado
        	        int squareSize = (int) (Math.min(widthS, heightS) * 0.5); // Tamaño relativo del cuadrado
        	        int squareX = (widthS - squareSize) / 2;
        	        int squareY = (heightS - squareSize) / 2;

        	        g2d.setColor(Color.GREEN);
        	        g2d.fillRect(squareX, squareY, squareSize, squareSize);

        	        // Agregar borde al cuadrado
        	        g2d.setColor(Color.BLACK); // Color del borde
        	        g2d.setStroke(new BasicStroke(1)); // Grosor del borde
        	        g2d.drawRect(squareX, squareY, squareSize, squareSize);
        	        break;

        	    case CIRCLE:
        	    	
        	    	 int widthC = getWidth();
             	     int heightC = getHeight();
             	     
        	        // Dibujar el círculo
        	        int circleSize = (int) (Math.min(widthC, heightC) * 0.5); // Tamaño relativo del círculo
        	        int circleX = (widthC - circleSize) / 2;
        	        int circleY = (heightC - circleSize) / 2;

        	        g2d.setColor(Color.BLUE);
        	        g2d.fillOval(circleX, circleY, circleSize, circleSize);

        	        // Agregar borde al círculo
        	        g2d.setColor(Color.BLACK); // Color del borde
        	        g2d.setStroke(new BasicStroke(1)); // Grosor del borde
        	        g2d.drawOval(circleX, circleY, circleSize, circleSize);
        	        break;
        	}

          }
        };

        // Agregar el panel de botones a la izquierda y el panel de figuras a la derecha
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(shapePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Renderer_Figuras();
    }
}
