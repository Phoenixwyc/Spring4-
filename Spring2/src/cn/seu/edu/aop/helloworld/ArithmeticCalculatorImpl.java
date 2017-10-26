package cn.seu.edu.aop.helloworld;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	@Override
	public int add(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("the method add begin with[" + i + " , " + j + "]");
		int result = i + j;
		System.out.println("the method add end with[" + i + " , " + j + "]");
		return result;
	}

	@Override
	public int sub(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("the method sub begin with[" + i + " , " + j + "]");
		int result = i - j;
		System.out.println("the method sub end with[" + i + " , " + j + "]");
		return result;
	}

	@Override
	public int mul(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("the method mul begin with[" + i + " , " + j + "]");
		int result = i * j;
		System.out.println("the method mul end with[" + i + " , " + j + "]");
		return result;
	}

	@Override
	public int div(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("the method add begin with[" + i + " , " + j + "]");
		int result = i / j;
		System.out.println("the method div end with[" + i + " , " + j + "]");
		return result;
	}

}
