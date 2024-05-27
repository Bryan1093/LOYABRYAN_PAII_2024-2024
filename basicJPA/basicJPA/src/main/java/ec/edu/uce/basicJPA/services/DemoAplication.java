package ec.edu.uce.basicJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoAplication {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(DemoAplication.class, args);
        //System.out.println("Hello World!");
    }
}
