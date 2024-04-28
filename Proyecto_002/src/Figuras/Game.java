/*
 * Autor: Bryan
 * Versión: 1.0
 */

package Figuras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que representa el panel de juego donde se desarrolla la lógica del juego Galaga.
 */
public class Game extends JPanel implements ActionListener, KeyListener {
    private static Game instance;
    private AlliedShip naveAliada;
    private EnemyShip[] navesEnemigas;
    private EnemyShip[] navesEnemigasAbajo;
    private boolean gameOver;
    private Timer timer;
    private ArrayList<EnemyBullet> balasEnemigas;
    private String nombreUsuario = "Sikario";
    private int vidas = 100;
    private int puntaje = 0;
    private boolean nombreUsuarioIngresado = false;
    private boolean win;
    private boolean victoria = false;
    

    /**
     * Constructor privado de la clase Juego para asegurar que solo haya una instancia.
     */
    public Game() {
        setBackground(Color.BLACK);
        naveAliada = new AlliedShip(250, 500, 60, 70, Color.WHITE, this);
        navesEnemigas = new EnemyShip[5];
        navesEnemigasAbajo = new EnemyShip[5];
        gameOver = false;

        // Posiciones iniciales de las naves enemigas
        for (int i = 0; i < navesEnemigas.length; i++) {
            navesEnemigas[i] = new EnemyShip(40 + i * 135, 50, 40, 40, Color.RED);
        }

        // Posiciones iniciales de las naves enemigas abajo
        for (int i = 0; i < navesEnemigasAbajo.length; i++) {
            navesEnemigasAbajo[i] = new EnemyShip(120 + i * 135, 150, 40, 40, Color.RED);
        }

        // Configurar temporizador para actualizar el juego
        timer = new Timer(1000 / 60, this); // Llamar al actionPerformed aproximadamente cada 60 veces por segundo
        timer.start();

        setFocusable(true); // Permitir que el panel tenga el foco
        addKeyListener(this); // Agregar KeyListener al panel
        balasEnemigas = new ArrayList<>();
    }
    
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Método estático para obtener la única instancia de la clase Juego.
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Método para ingresar el nombre de usuario si aún no se ha ingresado.
     */
    public void ingresarNombreUsuario() {
        if (!nombreUsuarioIngresado) {
            nombreUsuario = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:", "Nombre de usuario", JOptionPane.PLAIN_MESSAGE);
            if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
                nombreUsuario = "Usuario";
            }
            nombreUsuarioIngresado = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizar(); // Actualizar el estado del juego en cada iteración del temporizador
    }
    
    public void disparoAutomatico() {
        for (EnemyShip naveEnemiga : navesEnemigas) {
            // Verificar si la nave enemiga debe disparar
            if (naveEnemiga.getY() == 0 && !naveEnemiga.isDisparando()) {
                naveEnemiga.setDisparando(true);
                naveEnemiga.shoot(); // Llamar al método disparar() de la nave enemiga
            }
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            // Verificar si la nave enemiga debe disparar
            if (naveEnemiga.getY() == 0 && !naveEnemiga.isDisparando()) {
                naveEnemiga.setDisparando(true);
                naveEnemiga.shoot(); // Llamar al método disparar() de la nave enemiga
            }
        }
    }




    /**
     * Método para actualizar el estado del juego en cada iteración del temporizador.
     */
    public void actualizar() {
        naveAliada.moverBalas();
        disparoAutomatico();

        // Verificar colisiones y eliminar naves enemigas (arriba y abajo)
        for (int i = 0; i < naveAliada.getBalas().size(); i++) {
            AlliedBullet bala = naveAliada.getBalas().get(i);
            // Verificar colisión con naves enemigas arriba
            for (int j = 0; j < navesEnemigas.length; j++) {
                if (navesEnemigas[j] != null && Colisiones.colision(bala, navesEnemigas[j])) {
                    naveAliada.getBalas().remove(i); // Eliminar la bala
                    navesEnemigas[j] = null; // Eliminar la nave enemiga
                    puntaje += 10; // Sumar puntos al puntaje
                    break; // Salir del bucle interno
                }
            }
            // Verificar colisión con naves enemigas abajo
            for (int j = 0; j < navesEnemigasAbajo.length; j++) {
                if (navesEnemigasAbajo[j] != null && Colisiones.colision(bala, navesEnemigasAbajo[j])) {
                    naveAliada.getBalas().remove(i); // Eliminar la bala
                    navesEnemigasAbajo[j] = null; // Eliminar la nave enemiga
                    puntaje += 10; // Sumar puntos al puntaje
                    break; // Salir del bucle interno
                }
            }
        }

        // Eliminar naves enemigas nulas (arriba)
        navesEnemigas = Arrays.stream(navesEnemigas)
                .filter(Objects::nonNull)
                .toArray(EnemyShip[]::new);

        // Eliminar naves enemigas nulas (abajo)
        navesEnemigasAbajo = Arrays.stream(navesEnemigasAbajo)
                .filter(Objects::nonNull)
                .toArray(EnemyShip[]::new);


        // Mover y eliminar balas enemigas
        for (EnemyShip naveEnemiga : navesEnemigas) {
            naveEnemiga.move();
            if (naveEnemiga.getY() >= getHeight() * 0.66) { // 66% de la pantalla (600px)
                gameOver = true;
            }
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.move();
            if (naveEnemiga.getY() >= getHeight() * 0.66) { // 66% de la pantalla (600px)
                gameOver = true;
            }
        }

        
        if (navesEnemigas.length == 0 && navesEnemigasAbajo.length == 0) {
            setWin(true);
        }

        if (getNavesEnemigas().length == 0 && getNavesEnemigasAbajo().length == 0) {
            victoria = true;
        }


        repaint(); // Volver a dibujar el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        naveAliada.draw(g);

        for (EnemyShip naveEnemiga : navesEnemigas) {
            naveEnemiga.draw(g);
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.draw(g);
        }

        // Dibujar las balas de las naves enemigas
     // Dibujar las balas de las naves enemigas
        for (EnemyShip naveEnemiga : navesEnemigas) {
            for (EnemyBullet bala : naveEnemiga.getBalas()) {
                bala.draw(g);
            }
        }

     // Dibujar las balas de las naves enemigas
        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            for (EnemyBullet bala : naveEnemiga.getBalas()) {
                bala.draw(g);
            }
        }


        // Dibujar nombre del usuario y recuento de vidas en la esquina superior izquierda
        g.setColor(Color.WHITE);
        g.drawString("Usuario: " + nombreUsuario, 10, 20);
        g.drawString("Vida: " + vidas, 10, 40);

        // Dibujar puntaje en la esquina superior derecha
        g.drawString("Puntaje: " + puntaje, getWidth() - 100, 20);

        if (gameOver) {
            g.setColor(Color.WHITE);
            timer.stop(); // Detener el temporizador
            JOptionPane.showMessageDialog(this, "GAME OVER"); // Mostrar mensaje de GAME OVER
            System.exit(0); // Cerrar la aplicación
        }
        
        if (victoria) {
        	g.setFont(new Font("Arial", Font.BOLD, 30));
        	 
            g.setColor(Color.WHITE);
            g.drawString("YOU WIN!!", getWidth() / 2 - 50, getHeight() / 2);
            timer.stop(); // Detener el temporizador
            return; // Salir del método para evitar que se siga dibujando el juego
        }

        int yLinea = (int) (getHeight() * 0.66); // 66% de la altura total
        g.setColor(Color.WHITE);
        g.drawLine(0, yLinea, getWidth(), yLinea);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se implementa en este juego
    }

    @Override
    public void keyPressed(KeyEvent e) {
        naveAliada.keyPressed(e);
        repaint(); // Volver a dibujar la nave después de moverla
    }

    @Override
    public void keyReleased(KeyEvent e) {
        naveAliada.keyReleased(e);
        repaint(); // Volver a dibujar la nave después de moverla
    }

    public void agregarBalaEnemiga(EnemyBullet bala) {
        // Agregar la bala enemiga a alguna estructura de datos en Juego
        // Por ejemplo, podrías tener una lista de balas enemigas
        balasEnemigas.add(bala);
    }
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    public EnemyShip[] getNavesEnemigas() {
        return navesEnemigas;
    }

    public EnemyShip[] getNavesEnemigasAbajo() {
        return navesEnemigasAbajo;
    }


}
