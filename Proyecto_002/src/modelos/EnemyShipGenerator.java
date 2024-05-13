package modelos;

import java.awt.Color;
import java.util.Random;

public class EnemyShipGenerator {
    private int maxEnemies;
    private int currentEnemies;

    public EnemyShipGenerator(int maxEnemies) {
        this.maxEnemies = maxEnemies;
        this.currentEnemies = 0;
    }

    public EnemyShip[] generateEnemies(int amount) {
        if (currentEnemies + amount > maxEnemies) {
            amount = maxEnemies - currentEnemies;
        }

        EnemyShip[] newEnemies = new EnemyShip[amount];
        Random rand = new Random();

        for (int i = 0; i < amount; i++) {
            int x = rand.nextInt(800); // Genera una posición x aleatoria
            int y = rand.nextInt(200) - 200; // Genera una posición y arriba de la pantalla
            newEnemies[i] = new EnemyShip(x, y, 40, 40, Color.RED);
        }

        currentEnemies += amount;
        return newEnemies;
    }

    public void reset() {
        currentEnemies = 0;
    }

    public int getCurrentEnemies() {
        return currentEnemies;
    }
}
