package cn.seu.edu.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 要想使用FactoryBean，需要实现FactoryBean接口
 * @author Administrator
 *
 */
public class CarFactoryBean implements FactoryBean<Car>{
	
	private String brand;
	
	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	// 返回bean的对象
	@Override
	public Car getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand, 300000);
	}

	// 返回bean的类型
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	// 是否是单例的
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
