package cn.seu.edu.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.seu.edu.autowire.Address;
import cn.seu.edu.autowire.Car;
import cn.seu.edu.autowire.Person;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
		
//		Car car = ctx.getBean("car", Car.class);
//		Car car2 = ctx.getBean("car", Car.class);
//		System.out.println(car == car2);
	}

}
