package ec.uce.edu.basebeans;

import ec.uce.edu.basebeans.Contraller.Container;
import ec.uce.edu.basebeans.models.Hero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ec.uce.edu.basebeans.*")
public class AppConfig {
    @Bean
    public Hero hero(){
        return new Hero();
    }

    @Bean
    public Container container(){
        return new Container();
    }
}
