package cn.seu.edu.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.seu.edu.autowire.Address;
import cn.seu.edu.autowire.Person;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
		
		Address address = ctx.getBean("address", Address.class);
		System.out.println(address.toString());
		
		Address address2 = ctx.getBean("address2", Address.class);
		System.out.println(address2.toString());
		
		Address address3 = ctx.getBean("address3", Address.class);
		System.out.println(address3.toString());
		
		Address address4 = ctx.getBean("address4", Address.class);
		System.out.println(address4.toString());
		
		Person person = ctx.getBean("person", Person.class);
		System.out.println(person.toString());
		
		
	}

}
