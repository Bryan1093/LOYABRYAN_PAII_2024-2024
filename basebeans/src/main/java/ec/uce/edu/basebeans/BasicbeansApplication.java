package ec.uce.edu.basebeans;

import ec.uce.edu.basebeans.Contraller.Container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//@SpringBootApplication
//Forma de leer el bean
//@ComponentScan(basePackages = {"ec.edu.uce.models", "ec.edu.uce.ProyectoBeans"})
public class BasicbeansApplication {

	public static void main(String[] args) {
		//Se pone el nombre del paquete
		//ApplicationContext context = new AnnotationConfigApplicationContext(ProyectoBeansApplication.class);
		//Clase del tipo que quiero hacer el bean
		//Hero heroBean = context.getBean(Hero.class);
		//heroBean.draw();

		//Leer clases de config
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();
		//Hero hero = context.getBean(Hero.class);
		//hero.draw();

		Container container = context.getBean(Container.class);
		container.draw();
		//SpringApplication.run(ProyectoBeansApplication.class, args);
		//ApplicationContext context = SpringApplication.run(ProyectoBeansApplication.class, args);
		//Hero hero = context.getBean(Hero.class);
		//hero.draw();

		//Inversion de control IoC
		//Container container1 = context.getBean(Container.class);
		//container1.draw();
		//Container container = new Container(hero);
		//container.draw();


		// Cargar el contexto de la aplicación desde el archivo XML de configuración
		//ApplicationContext context = new ClassPathXmlApplicationContext("heroBeans.xml");
		// Obtener el bean Hero del contexto
		//Hero heroBean = (Hero) context.getBean("hero");
		// Utilizar el bean Hero
		//heroBean.draw();
		// Cerrar el contexto de la aplicación
		//((ClassPathXmlApplicationContext) context).close();


		//SpringApplication.run(ProyectoBeansApplication.class, args);
		//Hero hero = new Hero("user",100,0);
		//IDrawable hero = new Hero("user",100,0);
		//Role hero = new Hero("user",100,0);
		//Hero hero = new Hero("user",100,0);
		//hero.draw();
	}
}

/*
* BeanFactory factory = new ClassPathXmlApplicationContext("classpath:personaBeans.xml");

		System.out.println(factory.getBean("Persona1").toString());
		System.out.println(factory.getBean("Persona2").toString());
		System.out.println(factory.getBean("Persona3").toString());
		System.out.println(factory.getBean("Persona4").toString());
		System.out.println(factory.getBean("Persona5").toString());
		System.out.println(factory.getBean("Persona6").toString());
* */