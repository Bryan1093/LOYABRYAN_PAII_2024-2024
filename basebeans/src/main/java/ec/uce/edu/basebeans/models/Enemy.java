package ec.uce.edu.basebeans.models;

import org.springframework.stereotype.Service;

@Service("enemy")
public class Enemy extends Role implements Drawable{
    private String name;
    private int life;
    private int score;

    public Enemy(){
        super (5);
    }

    public Enemy(String name, int life, int score) {
        super(3);
        this.name = name;
        this.life = life;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void draw() {
        System.out.println("Dibujando Enemy con puntos: " + this.getCoordX().length);

    }

}