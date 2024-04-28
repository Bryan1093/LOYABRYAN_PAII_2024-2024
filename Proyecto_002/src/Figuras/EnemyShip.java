/*
 * Autor: Bryan
 * Versión: 1.0
 */

package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.Timer;

import Interface.IDrawable;
import Interface.IShoot;
import Interface.IDead;
import Interface.IMove;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnemyShip implements IDrawable, IMove, IDead, IShoot {
    private int x, y;                   // Posición de la nave enemiga en el eje x e y
    private int width, high;            // Ancho y alto de la nave enemiga
    private Color color;                // Color de la nave enemiga
    private Timer timer;                // Temporizador para generar disparos automáticos
    private int delay = 1000;           // Retardo entre disparos en milisegundos
    private boolean shootingUp;         // Indica si la nave está disparando actualmente
    private ArrayList<EnemyBullet> bullets;
    private int speedY = 1;         // Velocidad vertical de la nave enemiga
    private int movements = 0;        // Contador de movimientos

    public EnemyShip(int x, int y, int width, int high, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
        this.color = color;
        shootingUp = false;
        this.bullets = new ArrayList<>();

        // Inicializar el temporizador para generar disparos automáticos
        this.timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoot();
            }
        });
        this.timer.start(); // Iniciar el temporizador
    }

    @Override
    public void draw(Graphics g) {
        // Dibujar la nave enemiga como un polígono relleno
        int[] xPoints = {x, x + width / 2, x + width, x + width / 2};
        int[] yPoints = {y + high, y, y + high, y + high / 2};
        Polygon nave = new Polygon(xPoints, yPoints, 4);
        g.setColor(Color.GREEN);
        g.fillPolygon(nave);

        // Dibujar las balas de la nave enemiga
        for (EnemyBullet bala : bullets) {
            bala.draw(g);
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
    }

    public boolean intersecta(AlliedBullet bala) {
        Rectangle rectNave = new Rectangle(x, y, width, high);
        Rectangle rectBala = new Rectangle(bala.getX(), bala.getY(), bala.getAncho(), bala.getAlto());
        return rectNave.intersects(rectBala);
    }

    @Override
    public void dead() {
        // Reiniciar la posición de la nave enemiga
        y = 0;  // Puedes establecer la posición y a la original o a la que prefieras
        shootingUp = false; // Reiniciar el estado de disparo
        Colisiones.eliminarNaveEnemiga(Game.getInstance().getNavesEnemigas(), this); // Eliminar la nave enemiga de la lista
        if (Game.getInstance().getNavesEnemigas().length == 0 && Game.getInstance().getNavesEnemigasAbajo().length == 0) {
            Game.getInstance().setWin(true); // Indicar que el jugador ha ganado
        }
    }


    public void shoot() {
        // Calcular la posición de la bala enemiga
        int xBala = this.getX() + this.getAncho() / 2;
        int yBala = this.getY() + this.getAlto();

        // Crear una nueva bala enemiga y agregarla a la lista balasEnemigas
        EnemyBullet bala = new EnemyBullet(xBala, yBala, 5, 10, Color.GREEN);
        Game.getInstance().agregarBalaEnemiga(bala);
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
}
