package cn.seu.edu.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factory.xml");
		
		Car car = ctx.getBean("car1", Car.class);
		System.out.println(car);
		
		Car car2 = ctx.getBean("car2", Car.class);
		System.out.println(car2);
	}

}
