package ec.uce.edu.basebeans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BasebeansApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasebeansApplication.class, args);

		/*BeanFactory factoty = new ClassPathXmlApplicationContext("file:src/main/resources/beans.xml");
		Person p1= new Person(2, "JHON", 25);
		System.out.println(p1.toString());
		 */

		BeanFactory factoty = new ClassPathXmlApplicationContext("file:src/main/resources/beans.xml");
		System.out.println(factoty.getBean("Persona1"));
		System.out.println(factoty.getBean("Persona2"));
		System.out.println(factoty.getBean("Persona3"));
		System.out.println(factoty.getBean("Persona4"));
		System.out.println(factoty.getBean("Persona5"));
	}
}