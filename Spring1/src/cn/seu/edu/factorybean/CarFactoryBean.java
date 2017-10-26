package cn.seu.edu.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Ҫ��ʹ��FactoryBean����Ҫʵ��FactoryBean�ӿ�
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

	// ����bean�Ķ���
	@Override
	public Car getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand, 300000);
	}

	// ����bean������
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	// �Ƿ��ǵ�����
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
