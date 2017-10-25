package cn.seu.edu.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-autowire.xml");
		
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.toString());
		
		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.toString());
	}

}
