package cn.seu.edu.aop.helloworld.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLogProxy {

	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorLogProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	public ArithmeticCalculator getLogProxy() {
		ArithmeticCalculator proxy = null;
		
		//����������Ǹ������������
		ClassLoader loader = target.getClass().getClassLoader();
		// ������ʹ�õĽӿڣ�Ҳ������������Щ����
		Class<?>[] interfaces = new Class<?>[]{ArithmeticCalculator.class};
		// �����ô������ķ���ʱ��Ҫ��ִ����Щ����
		InvocationHandler h = new InvocationHandler() {

			/**
			 * proxy ���ڷ��صĶ���һ����invoke�����ж���ʹ�øö���
			 * method ���ڱ����õķ���
			 * args �����õķ�����Ҫ����Ĳ���
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				String methodName = method.getName();
				// ִ�з���
				Object result = null;
				try {
					// ǰ��֪ͨ
					// ��־
					System.out.println("The method " + methodName + " begins wirh " + Arrays.asList(args));
					result = method.invoke(target, args);
					//����֪ͨ
				} catch (Exception e) {
					// �쳣֪ͨ
					e.printStackTrace();
				}
				// ����֪ͨ
				// ��־
				System.out.println("The method " + methodName + " ends wirh " + result);
				return result;
			}
			
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
