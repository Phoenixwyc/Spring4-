package cn.seu.edu.aop.helloworld.aopimpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		
		ArithmeticCalculator arithmeticCalculator = ctx.getBean("arithmeticCalculatorImpl", ArithmeticCalculator.class);
		int result = arithmeticCalculator.add(1, 2);
		int result2 = arithmeticCalculator.mul(1, 3);
		int result3 = arithmeticCalculator.div(1, 0);
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
	}

}
