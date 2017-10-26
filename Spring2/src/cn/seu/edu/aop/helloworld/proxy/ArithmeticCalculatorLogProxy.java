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
		
		//代理对象有那个类加载器加载
		ClassLoader loader = target.getClass().getClassLoader();
		// 代理类使用的接口，也就是其中有哪些方法
		Class<?>[] interfaces = new Class<?>[]{ArithmeticCalculator.class};
		// 当调用代理对象的方法时需要做执行哪些代码
		InvocationHandler h = new InvocationHandler() {

			/**
			 * proxy 正在返回的对象，一般在invoke方法中都不使用该对象
			 * method 正在被调用的方法
			 * args 被调用的方法需要传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				String methodName = method.getName();
				// 执行方法
				Object result = null;
				try {
					// 前置通知
					// 日志
					System.out.println("The method " + methodName + " begins wirh " + Arrays.asList(args));
					result = method.invoke(target, args);
					//返回通知
				} catch (Exception e) {
					// 异常通知
					e.printStackTrace();
				}
				// 后置通知
				// 日志
				System.out.println("The method " + methodName + " ends wirh " + result);
				return result;
			}
			
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
