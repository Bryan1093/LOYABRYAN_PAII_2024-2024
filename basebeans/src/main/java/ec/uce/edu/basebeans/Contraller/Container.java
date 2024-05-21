package ec.uce.edu.basebeans.Contraller;

import ec.uce.edu.basebeans.models.Drawable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class Container {
    //@Autowired
    @Autowired
    @Qualifier("hero")
    //Hero hero;
            Drawable hero;
    @Autowired
    @Qualifier("enemy")
    //Enemy enemy;
    Drawable enemy;
    //@Autowired
    /*public Container(Hero hero, Enemy enemy){
    this.hero = hero;
    this.enemy = enemy;
}*/
    public Container(){

    }
    public void draw(){
        System.out.println("Aqui inicio al dibujar en el container....");
        this.hero.draw();
        this.enemy.draw();
    }
// o usar el @Autowired en los setters de los objetos
}