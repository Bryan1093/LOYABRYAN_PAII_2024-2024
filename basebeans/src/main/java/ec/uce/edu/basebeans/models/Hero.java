package ec.uce.edu.basebeans.models;

//Anotaciones Beans:
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service("hero")
public class Hero extends Role implements Drawable{
    //@Value("${hero.name:Walker77}")
    @Value("Sikario1093")
    private String name;
    private int life;
    private int score;

    public Hero(){
        super(3);
    }

    public Hero(String name, int life, int score) {
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
        System.out.println("Dibujando hero con: "+this.getCoordX().length+" puntos con nombre: "+this.name);
    }
}
