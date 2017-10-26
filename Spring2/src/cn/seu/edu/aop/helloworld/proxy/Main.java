package cn.seu.edu.aop.helloworld.proxy;

public class Main {
	public static void main(String[] args) {
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLogProxy(target).getLogProxy();
		int i = 2;
		int j = 1;
		int result = proxy.add(i, j);
		System.out.println(result);
		
		int result2 = proxy.div(i, j);
		System.out.println(result2);
	}

}
