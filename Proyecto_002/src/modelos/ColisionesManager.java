package modelos;

public class ColisionesManager {
    public static int verificarColisionesYEliminarNaves(AlliedShip naveAliada, EnemyShip[] navesEnemigas, EnemyShip[] navesEnemigasAbajo, int puntaje) {
        for (int i = 0; i < naveAliada.getBalas().size(); i++) {
            AlliedBullet bala = naveAliada.getBalas().get(i);
            for (int j = 0; j < navesEnemigas.length; j++) {
                if (navesEnemigas[j] != null && Colisiones.colision(bala, navesEnemigas[j])) {
                    naveAliada.getBalas().remove(i);
                    navesEnemigas[j] = null;
                    puntaje += 10;
                    break;
                }
            }
            for (int j = 0; j < navesEnemigasAbajo.length; j++) {
                if (navesEnemigasAbajo[j] != null && Colisiones.colision(bala, navesEnemigasAbajo[j])) {
                    naveAliada.getBalas().remove(i);
                    navesEnemigasAbajo[j] = null;
                    puntaje += 10;
                    break;
                }
            }
        }
        return puntaje;
    }
}