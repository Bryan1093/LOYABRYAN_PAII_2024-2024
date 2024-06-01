/*
 * Autor: Bryan
 * Versión: 1.0
 */

package modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.Timer;

import Interface.IDrawable;
import Interface.IShoot;
import controller.Contenedor;
import Interface.IDead;
import Interface.IMove;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class EnemyShip implements IDrawable, IMove, IDead, IShoot {
    private int x, y;                   // Posición de la nave enemiga en el eje x e y
    private int width, high;            // Ancho y alto de la nave enemiga
    private Color color;                // Color de la nave enemiga
    private Timer timer;                // Temporizador para generar disparos automáticos
    private int delay = 1000;           // Retardo entre disparos en milisegundos
    private boolean shootingUp;         // Indica si la nave está disparando actualmente
    private ArrayList<EnemyBullet> bullets;
    private int speedY = 1;         // Velocidad vertical de la nave enemiga
    private int speedX = 1;  
    private int movements = 0;        // Contador de movimientos
    private boolean restadoVida;
    private int direction = 1;

    public EnemyShip(int x, int y, int width, int high, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        this.color = color;
        shootingUp = false;
        bullets = new ArrayList<>();
        restadoVida = false;

        // Inicializar el temporizador para generar disparos automáticos
        this.timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoot();
            }
        });
        timer.start(); // Iniciar el temporizador
    }

    public int[] rotateXRight(int[] xPoints, int[] yPoints, int centerX, int centerY) {
        int[] rotatedX = new int[xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            rotatedX[i] = centerX + (yPoints[i] - centerY);
        }
        return rotatedX;
    }

    public int[] rotateYRight(int[] xPoints, int[] yPoints, int centerX, int centerY) {
        int[] rotatedY = new int[yPoints.length];
        for (int i = 0; i < yPoints.length; i++) {
            rotatedY[i] = centerY - (xPoints[i] - centerX);
        }
        return rotatedY;
    }



    @Override
    public void draw(Graphics g) {
        // Coordenadas originales de los puntos de la figura
        int[] xPoints = {x, x + width, x + width, x, x + width / 2};
        int[] yPoints = {y, y, y + high, y + high, y + high / 2};

        // Calcular el centro de la figura para la rotación
        int centerX = x + width / 2;
        int centerY = y + high / 2;

        // Rotar los puntos 90 grados a la derecha
        int[] rotatedXPoints = rotateXRight(xPoints, yPoints, centerX, centerY);
        int[] rotatedYPoints = rotateYRight(xPoints, yPoints, centerX, centerY);

        // Crear el polígono con las coordenadas rotadas
        Polygon nave = new Polygon(rotatedXPoints, rotatedYPoints, xPoints.length);
        g.setColor(Color.GREEN);
        g.fillPolygon(nave);

        // Dibujar las balas de la nave enemiga
        for (EnemyBullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    @Override
    public void move() {
        // Incrementar un contador de movimientos
        movements++;
        if (movements % 2 == 0) { // Mover la nave cada 2 actualizaciones de movimiento
            y += speedY; // Mover la nave hacia abajo
        }
        
        // Controlar el límite de la pantalla para reiniciar la posición de la nave
        if (y > 600) {
            y = 0;
            shootingUp = false; // Reiniciar el estado de disparo
        }
        
        x += speedX * direction; // Mover la nave en la dirección actual

        // Cambiar la dirección si la nave llega a los límites de la pantalla
        if (x <= 0 || x + width >= 800) {
            direction *= -1; // Cambiar la dirección
            y += 10; // Bajar la nave un poco al cambiar de dirección
        }

        // Mover las balas enemigas
        for (EnemyBullet bullet : bullets) {
            bullet.moverAbajo();
        }

        // Eliminar balas que salieron de la pantalla
        Iterator<EnemyBullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            EnemyBullet bullet = iter.next();
            if (bullet.getY() > 600) {
                iter.remove();
            }
        }
    }



    public boolean intersecta(AlliedBullet bala) {
        Rectangle rectNave = new Rectangle(x, y, width, high);
        Rectangle rectBala = new Rectangle(bala.getX(), bala.getY(), bala.getAncho(), bala.getAlto());
        return rectNave.intersects(rectBala);
    }

    @Override
    public void dead() {
        // Reiniciar la posición de la nave enemiga
        y = 0; 
        shootingUp = false; // Reiniciar el estado de disparo
        Colisiones.eliminarNaveEnemiga(Contenedor.getInstance().getNavesEnemigas(), this); // Eliminar la nave enemiga de la lista
        if (Contenedor.getInstance().getNavesEnemigas().length == 0 && Contenedor.getInstance().getNavesEnemigasAbajo().length == 0) {
            Contenedor.getInstance().setWin(true); // Indicar que el jugador ha ganado
        }
    }


    @Override
    public EnemyBullet shoot() {
    	// Calcular la posición de la bala enemiga
        int xBullet = this.x + this.width / 2;
        int yBullet = this.y + this.high;

        // Crear una nueva bala enemiga y agregarla a la lista de balas
        EnemyBullet bullet = new EnemyBullet(xBullet, yBullet, 5, 10, Color.WHITE);
        bullet.setSpeedY(1); // Velocidad hacia abajo
        bullets.add(bullet);
		return bullet;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getAncho() {
        return width;
    }

    public int getAlto() {
        return high;
    }

    public void setDisparando(boolean disparando) {
        this.shootingUp = disparando;
    }

    public boolean isDisparando() {
        return shootingUp;
    }

    public ArrayList<EnemyBullet> getBalas() {
        return bullets;
    }
    
    // Getter and setter for restadoVida
    public boolean isRestadoVida() {
        return restadoVida;
    }

    public void setRestadoVida(boolean restadoVida) {
        this.restadoVida = restadoVida;
    }
    
    public void resetRestadoVida() {
        this.restadoVida = false;
    }
}
