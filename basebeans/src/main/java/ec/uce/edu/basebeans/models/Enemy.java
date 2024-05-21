package ec.uce.edu.basebeans.models;

import org.springframework.stereotype.Service;

@Service("enemy")
public class Enemy extends Role implements Drawable{
    private int life;
    private int reward;
    @Override
    public void draw() {
        System.out.println("Dibujando Enemy con: "+this.getCoordX().length+" puntos");

    }
    public Enemy(){
        super(5);
    }

    public Enemy(int life, int reward) {
        this.life = life;
        this.reward = reward;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
