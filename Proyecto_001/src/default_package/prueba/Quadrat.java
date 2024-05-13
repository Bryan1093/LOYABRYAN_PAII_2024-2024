package prueba;

import java.util.Random;

import javax.swing.JPanel;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Quadrat {
    private GLAutoDrawable drawable;
    private boolean dibuixarQuadrat;
    
    // Crear una instancia de Random
    Random random = new Random();

    // Generar valores aleatorios para cada componente de color
    float red = random.nextFloat(); // Valor entre 0.0 y 1.0
    float green = random.nextFloat(); // Valor entre 0.0 y 1.0
    float blue = random.nextFloat(); // Valor entre 0.0 y 1.0
    
    // Constructor que acepta un GLAutoDrawable como argumento
    public Quadrat(GLAutoDrawable drawable) {
        this.drawable = drawable;
        this.dibuixarQuadrat = false;
    }

    public void dibujarQuadrat() {
        dibuixarQuadrat = true;
        drawable.display(); // Vuelve a dibujar el panel
    }


    public void dibujar(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        if (dibuixarQuadrat) {
            gl.glBegin(GL2.GL_QUADS); // Dibujar el cuadrado
            gl.glColor3f(red, green, blue);
            gl.glVertex2f(-0.5f, 0.5f); // Vértice superior izquierdo
            gl.glVertex2f(0.5f, 0.5f); // Vértice superior derecho
            gl.glVertex2f(0.5f, -0.5f); // Vértice inferior derecho
            gl.glVertex2f(-0.5f, -0.5f); // Vértice inferior izquierdo
            gl.glEnd();
        }
    }

    public void limpiarDibujo(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Establecer el color de fondo como negro
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT); // Limpiar el búfer de color
    }
}