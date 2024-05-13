package prueba;

import java.util.Random;

import javax.swing.JPanel;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Triangle {
    private GLAutoDrawable drawable;
    private boolean dibuixarTriangle;
   
 // Crear una instancia de Random
    Random random = new Random();

    // Generar valores aleatorios para cada componente de color
    float red = random.nextFloat(); // Valor entre 0.0 y 1.0
    float green = random.nextFloat(); // Valor entre 0.0 y 1.0
    float blue = random.nextFloat(); // Valor entre 0.0 y 1.0
    
    // Constructor que acepta un GLAutoDrawable como argumento
    public Triangle(GLAutoDrawable drawable) {
        this.drawable = drawable;
        this.dibuixarTriangle = true;
    }


    public void dibujarTriangulo() {
    	Triangle triangulo = null;
    	drawable.display(); // Vuelve a dibujar el panel
    }
    
    public void dibujar(GLAutoDrawable drawable) {
    	
        final GL2 gl = drawable.getGL().getGL2();

        if (dibuixarTriangle) {
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor3f(red, green, blue); // Colores aleatorios
            gl.glVertex2f(0f, 0.8f); // VS
            gl.glVertex2f(-0.8f, -0.7f); // VI
            gl.glVertex2f(0.8f, -0.7f); // VD
            gl.glEnd();
        }
    
    }

    public void limpiarDibujo(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT); 
    }
    
    

}
