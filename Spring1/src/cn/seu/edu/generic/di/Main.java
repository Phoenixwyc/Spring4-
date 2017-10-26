package cn.seu.edu.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");
		
		UserService userService = ctx.getBean("userService", UserService.class);
		userService.add();
	}

}
