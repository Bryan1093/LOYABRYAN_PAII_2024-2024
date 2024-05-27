package ec.edu.uce.basicJPA.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceWeb {
    @RequestMapping(value = "/miurl", method = RequestMethod.GET)
    public String miPrimermEndPoint(){
        return "Hola mundo desde mi primer endpoint";
    }

    @RequestMapping(value = "/miurl", method = RequestMethod.POST)
    public String miSegundoEndPoint(){
        return "Hola hijos de puta desde mi segundo endpoint";
    }
}
