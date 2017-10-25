package cn.seu.edu.cn.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		
//		HelloWorld helloWorld = ctx.getBean("helloWorld", HelloWorld.class);
//		helloWorld.hello();
		
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car.toString());
		
		Car car2 = ctx.getBean("car2", Car.class);
		System.out.println(car2.toString());
		
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.toString());
		
		Person person2 = ctx.getBean("person2", Person.class);
		System.out.println(person2.toString());
		
		Person person3 = ctx.getBean("person3", Person.class);
		System.out.println(person3.toString());
		
		Person person4 = ctx.getBean("person4", Person.class);
		System.out.println(person4.toString());
		
		PersonCollections personCollections = ctx.getBean("personCollections", PersonCollections.class);
		System.out.println(personCollections.toString());
		
		PersonCollectionsMap personCollectionsMap = ctx.getBean("personCollectionsMap", PersonCollectionsMap.class);
		System.out.println(personCollectionsMap.toString());
		
		DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
		System.out.println(dataSource.toString());
		
		PersonCollections personCollections2 = ctx.getBean("personCollectionsUtil", PersonCollections.class);
		System.out.println(personCollections2.toString());
		
	}

}
