package cn.seu.edu.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factorybean.xml");
		
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car);
	}

}
