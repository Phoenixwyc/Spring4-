package cn.seu.edu.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
		
//		Car car = ctx.getBean("car", Car.class);
//		Car car2 = ctx.getBean("car", Car.class);
//		System.out.println(car == car2);
	}

}
