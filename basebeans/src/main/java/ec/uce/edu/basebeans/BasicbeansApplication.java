package ec.uce.edu.basebeans;

import ec.uce.edu.basebeans.models.Enemy;
import ec.uce.edu.basebeans.models.Hero;
import ec.uce.edu.basebeans.Contraller.Container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
//@ComponentScan(basePackages = {"ec.uce.edu.basebeans.models","ec.uce.edu.basebeans"})
public class BasicbeansApplication {

	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/api/v1");
//		SpringApplication.run(BasicbeansApplication.class, args);

		SpringApplication.run(BasicbeansApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(BasicbeansApplication.class);


		//sedo el control
		 /*Hero heroBean = context.getBean(Hero.class);
		 heroBean.draw();*/

		/*
		//inversion de control
		Container container = context.getBean(Container.class);
		container.draw();
		 */

		Container container = context.getBean(Container.class);
		container.draw();

/*
		//aqui controlo la creacion de los objetos
		Container container = new Container(heroBean) ;
		container.draw();

 */


		/*Hero hero = new Hero("user",100,0);
		hero.draw();*/

//		BeanFactory factory = new ClassPathXmlApplicationContext("file:src/main/resources/personabeans.xml");
//		System.out.println(factory.getBean("persona1"));
//		System.out.println(factory.getBean("persona2"));
//		System.out.println(factory.getBean("calificacion1"));
//		System.out.println(factory.getBean("curso1"));
//		System.out.println(factory.getBean("estudiante1"));
//		System.out.println(factory.getBean("materia1"));
//		System.out.println(factory.getBean("profesor1"));
	}
}