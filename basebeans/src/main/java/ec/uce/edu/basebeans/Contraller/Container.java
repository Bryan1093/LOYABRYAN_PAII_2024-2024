package ec.uce.edu.basebeans.Contraller;

import ec.uce.edu.basebeans.models.Drawable;
import ec.uce.edu.basebeans.models.Enemy;
import ec.uce.edu.basebeans.models.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class Container {


    /*
    @Autowired
    Drawable hero;
    @Autowired
    Drawable enemy;

     */

    @Autowired
    @Qualifier("hero")
    Drawable hero;

    @Autowired
    @Qualifier("enemy")
    Drawable enemy;

    public Container() {

    }

    @Autowired
    public Container(Hero hero, Enemy enemy) {
        this.hero=hero;
        this.enemy=enemy;
    }
    public void draw(){
        System.out.println("Aqui inicio a dibujar en el contenedor");
        this.hero.draw();
        this.enemy.draw();
    }
/*
    public Hero getHero() {
        return hero;
    }

 */
/*
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    /*
/*
    public Enemy getEnemy() {
        return enemy;
    }


 */
/*
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

 */

}
