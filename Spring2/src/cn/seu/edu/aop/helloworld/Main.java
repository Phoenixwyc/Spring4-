package cn.seu.edu.aop.helloworld;

public class Main {
	public static void main(String[] args) {
		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
		int i = 2;
		int j = 1;
		int result = arithmeticCalculator.add(i, j);
		System.out.println(result);
		
		int result2 = arithmeticCalculator.div(i, j);
		System.out.println(result2);
	}

}
