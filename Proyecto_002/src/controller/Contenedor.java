/*
 * Autor: Bryan
 * Versión: 1.0
 */

package controller;

import javax.swing.*;

import modelos.AlliedBullet;
import modelos.AlliedShip;
import modelos.Colisiones;
import modelos.ColisionesManager;
import modelos.EnemyBullet;
import modelos.EnemyShip;
import modelos.EnemyShipGenerator;
import modelos.GameOverManager;
import modelos.HealthBar;
import modelos.Line;
import modelos.Usuario;
import modelos.VictoriaManager;
import modelos.Vida;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Clase que representa el panel de juego donde se desarrolla la lógica del juego Galaga.
 */
public class Contenedor extends JPanel implements ActionListener, KeyListener {
    private static Contenedor instance;
    private AlliedShip naveAliada;
    private EnemyShip[] navesEnemigas;
    private EnemyShip[] navesEnemigasAbajo;
    private boolean gameOver;
    private ArrayList<EnemyBullet> balasEnemigas;
    private Usuario usuario;
    private Vida vida;
    private int puntaje = 0;
    private boolean nombreUsuarioIngresado = false;
    private boolean win;
    private boolean victoria = false;
    private HealthBar healthBar;
    private static final int PLAYER_TOP_LIMIT = 2 * HEIGHT / 3 + 21; 
    private static final int LINE_Y_POSITION = 2 * HEIGHT / 3; 
    private EnemyShipGenerator enemyShipGenerator;
    private Set<EnemyShip> navesConVidaRestadaTotal = new HashSet<>();
    private Timer enemyShootTimer;
    

    

    /**
     * Constructor privado de la clase Juego para asegurar que solo haya una instancia.
     */
    public Contenedor() {
        setBackground(Color.BLACK);
        naveAliada = new AlliedShip(250, 500, 60, 70, Color.WHITE, this);
        navesEnemigas = new EnemyShip[5];
        navesEnemigasAbajo = new EnemyShip[5];
        balasEnemigas = new ArrayList<>();
        gameOver = false;
        
        usuario = new Usuario("Sikario"); // Inicializar con el nombre deseado
        vida = new Vida(100); // Inicializar con la cantidad de vidas deseada

        // Posiciones iniciales de las naves enemigas
        for (int i = 0; i < navesEnemigas.length; i++) {
            navesEnemigas[i] = new EnemyShip(40 + i * 135, 50, 40, 40, Color.RED);
        }

        // Posiciones iniciales de las naves enemigas abajo
        for (int i = 0; i < navesEnemigasAbajo.length; i++) {
            navesEnemigasAbajo[i] = new EnemyShip(120 + i * 135, 150, 40, 40, Color.RED);
        }

        // Configurar temporizador para actualizar el juego
        enemyShootTimer = new Timer(1000 / 60, this); // Llamar al actionPerformed aproximadamente cada 60 veces por segundo
        enemyShootTimer.start();

        setFocusable(true); // Permitir que el panel tenga el foco
        addKeyListener(this); // Agregar KeyListener al panel
        balasEnemigas = new ArrayList<>();
        
     // Inicializar la barra de vida
        healthBar = new HealthBar(60, 31, 100, 10, 100); // Por ejemplo, una barra de vida de 200x20 con un máximo de 100
        
        enemyShipGenerator = new EnemyShipGenerator(100); // Puedes ajustar el máximo de naves enemigas según tus necesidades
        
        enemyShootTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (EnemyShip naveEnemiga : navesEnemigas) {
                    if (!naveEnemiga.isDisparando()) {
                        naveEnemiga.setDisparando(true);
                        naveEnemiga.shoot(); // Llama al método shoot() de la nave enemiga

                    }
                }

                for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
                    if (!naveEnemiga.isDisparando()) {
                        naveEnemiga.setDisparando(true);
                        naveEnemiga.shoot(); // Llama al método shoot() de la nave enemiga

                    }
                }
            }
        });
        
        EnemyBullet[] arrayBalas = balasEnemigas.toArray(new EnemyBullet[1]);

        enemyShootTimer.start(); // Inicia el temporizador

    
    }
    
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Método estático para obtener la única instancia de la clase Juego.
     */
    public static Contenedor getInstance() {
        if (instance == null) {
            instance = new Contenedor();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actualizar(); // Actualizar el estado del juego en cada iteración del temporizador
    }
    
    public void disparoAutomatico() {
        for (EnemyShip naveEnemiga : navesEnemigas) {
            if (!naveEnemiga.isDisparando()) {
                naveEnemiga.setDisparando(true);
                EnemyBullet bala = naveEnemiga.shoot();
                if (bala != null) {
                    agregarBalaEnemiga(bala);
                }
            }
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            if (!naveEnemiga.isDisparando()) {
                naveEnemiga.setDisparando(true);
                EnemyBullet bala = naveEnemiga.shoot();
                if (bala != null) {
                    agregarBalaEnemiga(bala);
                }
            }
        }
    }



    public void regenerarNavesEnemigas(int cantidad) {
        EnemyShip[] nuevasNaves = enemyShipGenerator.generateEnemies(cantidad);
        for (int i = 0; i < nuevasNaves.length; i++) {
            if (navesEnemigas[i] == null) {
                navesEnemigas[i] = nuevasNaves[i];
                navesEnemigas[i].resetRestadoVida(); // Reiniciar el estado de restadoVida
            }
        }
    }

    public void moverBalasEnemigas() {
        // Mover y eliminar balas enemigas (arriba)
        for (EnemyShip naveEnemiga : navesEnemigas) {
            if (naveEnemiga != null) {
                naveEnemiga.moveBullets();
                Iterator<EnemyBullet> iterator = naveEnemiga.getBalas().iterator();
                while (iterator.hasNext()) {
                    EnemyBullet bala = iterator.next();
                    bala.moverAbajo(); // Mover la bala hacia abajo
                    if (bala.getY() >= getHeight()) {
                        iterator.remove(); // Marcar la bala para eliminar
                    }
                }
            }
        }

        // Mover y eliminar balas enemigas (abajo)
        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            if (naveEnemiga != null) {
                naveEnemiga.moveBullets();
                Iterator<EnemyBullet> iterator = naveEnemiga.getBalas().iterator();
                while (iterator.hasNext()) {
                    EnemyBullet bala = iterator.next();
                    bala.moverAbajo(); // Mover la bala hacia abajo
                    if (bala.getY() >= getHeight()) {
                        iterator.remove(); // Marcar la bala para eliminar
                    }
                }
            }
        }
    }




    /**
     * Método para actualizar el estado del juego en cada iteración del temporizador.
     */
    public void actualizar() {
        naveAliada.moverBalas();
        disparoAutomatico();
        puntaje = ColisionesManager.verificarColisionesYEliminarNaves(naveAliada, navesEnemigas, navesEnemigasAbajo, puntaje);
        moverBalasEnemigas();

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
            if (naveEnemiga.getY() >= getHeight() * 0.66 && !navesConVidaRestadaTotal.contains(naveEnemiga)) {
                vida.restarVida(10); // Restar 10 de vida
                navesConVidaRestadaTotal.add(naveEnemiga);
                naveEnemiga.setRestadoVida(true);
                naveEnemiga.moveBullets();
            }
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.move();
            if (naveEnemiga.getY() >= getHeight() * 0.66 && !navesConVidaRestadaTotal.contains(naveEnemiga)) {
                vida.restarVida(10); // Restar 10 de vida
                navesConVidaRestadaTotal.add(naveEnemiga);
                naveEnemiga.setRestadoVida(true);
                naveEnemiga.moveBullets();
            }
        }

        // Verificar si todas las naves han sido eliminadas y regenerarlas
        if (navesEnemigas.length == 0 && navesEnemigasAbajo.length == 0) {
            navesEnemigas = enemyShipGenerator.generateEnemies(5); // Generar 5 nuevas naves enemigas arriba
            navesEnemigasAbajo = enemyShipGenerator.generateEnemies(5); // Generar 5 nuevas naves enemigas abajo
        }

        if (getNavesEnemigas().length == 0 && getNavesEnemigasAbajo().length == 0) {
            victoria = true;
        }
        
        // Verificar si la vida llegó a 0 y mostrar Game Over
        if (vida.getCantidad() <= 0) {
            gameOver = true;
            enemyShootTimer.stop();
        }

        repaint(); // Volver a dibujar el panel
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        naveAliada.draw(g);

        for (EnemyShip naveEnemiga : navesEnemigas) {
            naveEnemiga.draw(g);
            naveEnemiga.shoot();
        }

        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            naveEnemiga.draw(g);
            naveEnemiga.shoot();
        }

     // Dibujar las balas de las naves enemigas
        for (EnemyShip naveEnemiga : navesEnemigas) {
            for (EnemyBullet bala : naveEnemiga.getBalas()) {
                bala.draw(g);
            }
        }

        // Dibujar las balas de las naves enemigas abajo
        for (EnemyShip naveEnemiga : navesEnemigasAbajo) {
            for (EnemyBullet bala : naveEnemiga.getBalas()) {
                bala.draw(g);
            }
        }

        
        //Usuario y Vidas
        
        g.setColor(Color.WHITE);
        g.drawString("Usuario: " + usuario.getNombre(), 10, 20);
        g.drawString("Vida: " + vida.getCantidad(), 10, 40);

        // Dibujar puntaje en la esquina superior derecha
        g.drawString("Puntaje: " + puntaje, getWidth() - 100, 20);
        
        
        //Derrota o Victoria
        
        if (gameOver) {
            GameOverManager.dibujarGameOver(g);
            enemyShootTimer.stop();
            return;
        }

        if (victoria) {
            VictoriaManager.dibujarVictoria(g, getWidth(), getHeight());
            enemyShootTimer.stop();
            return;
        }
        
        // Dibujar la línea que divide la pantalla en dos tercios
        int yLinea = (int) (getHeight() * 0.66); // 66% de la altura total
        Line linea = new Line(0, yLinea, getWidth(), yLinea, Color.WHITE);
        linea.draw(g);
        
        // Dibujar la barra de vida
        healthBar.draw(g); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se implementa en este juego
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	
        naveAliada.keyPressed(e);
        

        // Ajustar la cantidad de movimiento hacia la izquierda y hacia la derecha
        int moveAmount = 10; // Puedes ajustar este valor según sea necesario

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (naveAliada.getX() > moveAmount) { // Verificar que no se salga de la pantalla
                naveAliada.setX(naveAliada.getX() - moveAmount);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (naveAliada.getX() < getWidth() - naveAliada.getAncho() - moveAmount) { // Verificar que no se salga de la pantalla
                naveAliada.setX(naveAliada.getX() + moveAmount);
            }
        }
        
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
