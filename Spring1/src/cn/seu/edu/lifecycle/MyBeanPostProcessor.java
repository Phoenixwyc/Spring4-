package cn.seu.edu.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessAfterInitialization " + arg0 + " , " + arg1);
		// 这里需要返回处理后的bean，可以是原来的bean，也可以是修改后的bean，甚至可以是新创建的bean
		Car car = new Car();
		car.setBrand("RR");
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessBeforeInitialization " + arg0 + " , " + arg1);
		Car car = new Car();
		car.setBrand("BMW");
		return car;
	}

}
