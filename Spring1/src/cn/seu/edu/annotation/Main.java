package cn.seu.edu.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.seu.edu.annotation.controller.UserController;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
//		TestObject testObject = ctx.getBean("testObject", TestObject.class);
//		System.out.println(testObject);
//		
		UserController userController = ctx.getBean("userController", UserController.class);
		System.out.println(userController);
		userController.execute();
//		
//		UserService userService = ctx.getBean("userService", UserService.class);
//		System.out.println(userService);
		
//		UserRespository userRespository = ctx.getBean("userRespository", UserRespository.class);
//		System.out.println(userRespository);
		
		
	}

}
