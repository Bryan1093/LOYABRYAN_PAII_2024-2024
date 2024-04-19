package prueba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


public class Render extends JFrame {
	
	private enum Shape {TRIANGLE, SQUARE, CIRCLE}
    private Shape currentShape = Shape.TRIANGLE;

    //Instancias para el panel y las figuras
    private GLJPanel glPanel; 
    private Triangle triangle;
    private Quadrat quadrat; 
    private Cercle cercle;

    public Render() {
        setTitle("Figuras con JOGL en JAVA");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        glPanel = new GLJPanel();
        glPanel.addGLEventListener(new GLEventListener() {
        	
            @Override
            public void init(GLAutoDrawable drawable) {}

            @Override
            public void dispose(GLAutoDrawable drawable) {}

            public void display(GLAutoDrawable drawable) {
            	
                GL2 gl2 = drawable.getGL().getGL2();
                gl2.glClearColor(1.0f, 0.7529f, 0.7961f, 1.0f);
                gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);

                switch (currentShape) {
                
                    case TRIANGLE:
                        if (triangle != null) {
                            triangle.dibujar(drawable);                            
                        }
                        break;
                        
                    case SQUARE:
                        if (quadrat != null) {
                            quadrat.dibujar(drawable);                            
                        }
                        break;
                        
                    case CIRCLE:
                        if (cercle != null) {
                            cercle.dibujar(drawable);                 
                        }
                        break;
                }
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
        });

        getContentPane().setLayout(new GridLayout(1,2));;

      //Panel
        JPanel botones = new JPanel();
        botones.setLayout(new BoxLayout(botones, BoxLayout.Y_AXIS));
        getContentPane().add(botones, BorderLayout.WEST); // Añadir el panel de botones al lado izquierdo
        getContentPane().add(glPanel);

        //Botones
        JButton botódeTriangle = new JButton("Triangle");
        botódeTriangle.setAlignmentX(Component.CENTER_ALIGNMENT);
        botódeTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangle = new Triangle(glPanel);
                currentShape = Shape.TRIANGLE;
                triangle.dibujarTriangulo();
                glPanel.repaint();
            }
        });

        JButton botódeQuadrat = new JButton("Quadrat");
        botódeQuadrat.setAlignmentX(Component.CENTER_ALIGNMENT);
        botódeQuadrat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quadrat = new Quadrat(glPanel);
                currentShape = Shape.SQUARE;
                quadrat.dibujarQuadrat();
                glPanel.repaint();
            }
        });

        JButton botódeCercle = new JButton("Cercle");
        botódeCercle.setAlignmentX(Component.CENTER_ALIGNMENT);
        botódeCercle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cercle = new Cercle(glPanel);
                currentShape = Shape.CIRCLE;
                cercle.dibujarCercle();
                glPanel.repaint();
            }
        });

        // Agregar los botones al panel
        botones.add(Box.createVerticalStrut(50)); // Espacio vertical entre los botones
        botones.add(botódeTriangle);
        botones.add(Box.createVerticalStrut(80));
        botones.add(botódeQuadrat);
        botones.add(Box.createVerticalStrut(80));
        botones.add(botódeCercle);

        setVisible(true); // Hace visible la ventana
    }

    // Método principal, crea una instancia de Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Render();
            }
        });
    }
}