package prueba;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLJPanel;

public class Cercle{
    private GLAutoDrawable drawable;
    private boolean dibuixarCercle;
    
    // Crear una instancia de Random
    Random random = new Random();
    private ArrayList<Float> verticesAL = new ArrayList<>();

    // Generar valores aleatorios para cada componente de color
    float red = random.nextFloat(); // Valor entre 0.0 y 1.0
    float green = random.nextFloat(); // Valor entre 0.0 y 1.0
    float blue = random.nextFloat(); // Valor entre 0.0 y 1.0
    
    public void dibujarCercle() {
        dibuixarCercle = true;
        drawable.display(); // Vuelve a dibujar el panel
    }

    // Constructor que acepta un GLAutoDrawable como argumento
    public Cercle(GLAutoDrawable drawable) {
        this.drawable = drawable;
        this.dibuixarCercle = false;
    }

    public void dibujar(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        if (dibuixarCercle) {

           
            int segmentos = 100;

            gl.glBegin(GL2.GL_TRIANGLE_FAN); 
            gl.glColor3f(red, green, blue);
            
            // Centro del círculo
            gl.glVertex2f(0.0f, 0.0f); 

            // Calcular los vértices del círculo
            for (int i = 0; i <= segmentos; i++) {
                double angle = 2.0 * Math.PI * i / segmentos;
                float x = (float) Math.cos(angle);
                float y = (float) Math.sin(angle);
                gl.glVertex2f(x, y);


                }
            
            float [] vertices = new float[verticesAL.size()];
            for (int i=0;i<verticesAL.size();i++) {
                vertices[i] =  verticesAL.get(i);
                
            }

            gl.glEnd();
        }
    }

    public void limpiarDibujo(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    }
}
