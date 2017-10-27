package cn.seu.edu.aop.helloworld.aopxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aop.xml");

		ArithmeticCalculator arithmeticCalculator = ctx.getBean("arithmeticCalculator", ArithmeticCalculator.class);
		int result = arithmeticCalculator.add(1, 2);
		int result2 = arithmeticCalculator.mul(1, 3);
		int result3 = arithmeticCalculator.div(1, 0);
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
	}

}
