package cn.seu.edu.SpEL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-SpEL.xml");
		
		Address address = ctx.getBean("address", Address.class);
		System.out.println(address.toString());
		
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car.toString());

		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.toString());
	}

}
