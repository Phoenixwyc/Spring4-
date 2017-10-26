package cn.seu.edu.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
		
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car);
		
		// ¹Ø±ÕÈÝÆ÷
		ctx.close();
	}

}
