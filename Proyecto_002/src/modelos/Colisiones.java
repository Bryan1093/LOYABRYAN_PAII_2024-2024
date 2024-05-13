/*
 * Autor: Bryan
 * Versión: 1.0
 */

package modelos;

import java.awt.Rectangle;

/**
 * Clase que proporciona métodos estáticos para detectar colisiones entre objetos del juego.
 */
public class Colisiones {
    /**
     * Verifica si hay colisión entre una nave aliada y una nave enemiga.
     * @param naveAliada La nave aliada.
     * @param naveEnemiga La nave enemiga.
     * @return true si hay colisión, false en caso contrario.
     */
    public static boolean colision(AlliedShip naveAliada, EnemyShip naveEnemiga) {
        Rectangle rectNaveAliada = new Rectangle(naveAliada.getX(), naveAliada.getY(), naveAliada.getAncho(), naveAliada.getAlto());
        Rectangle rectNaveEnemiga = new Rectangle(naveEnemiga.getX(), naveEnemiga.getY(), naveEnemiga.getAncho(), naveEnemiga.getAlto());
        return rectNaveAliada.intersects(rectNaveEnemiga);
    }

    /**
     * Verifica si hay colisión entre una bala aliada y una nave enemiga.
     * @param balaAliada La bala aliada.
     * @param naveEnemiga La nave enemiga.
     * @return true si hay colisión, false en caso contrario.
     */
    public static boolean colision(AlliedBullet balaAliada, EnemyShip naveEnemiga) {
        Rectangle rectBalaAliada = new Rectangle(balaAliada.getX(), balaAliada.getY(), balaAliada.getAncho(), balaAliada.getAlto());
        Rectangle rectNaveEnemiga = new Rectangle(naveEnemiga.getX(), naveEnemiga.getY(), naveEnemiga.getAncho(), naveEnemiga.getAlto());
        return rectBalaAliada.intersects(rectNaveEnemiga);
    }

    /**
     * Elimina una nave enemiga de un arreglo de naves enemigas.
     * @param navesEnemigas Arreglo de naves enemigas.
     * @param naveEnemiga Nave enemiga a eliminar.
     */
    public static void eliminarNaveEnemiga(EnemyShip[] navesEnemigas, EnemyShip naveEnemiga) {
        for (int i = 0; i < navesEnemigas.length; i++) {
            if (navesEnemigas[i] == naveEnemiga) {
                navesEnemigas[i] = null;
                break;
            }
        }
    }
    
    /**
     * Verifica si hay colisión entre una bala aliada y un arreglo de naves enemigas.
     * @param balaAliada La bala aliada.
     * @param navesEnemigas Arreglo de naves enemigas.
     * @return true si hay colisión, false en caso contrario.
     */
    public static boolean colision(AlliedBullet balaAliada, EnemyShip[] navesEnemigas) {
        for (EnemyShip naveEnemiga : navesEnemigas) {
            if (naveEnemiga != null && colision(balaAliada, naveEnemiga)) {
                return true;
            }
        }
        return false;
    }
}