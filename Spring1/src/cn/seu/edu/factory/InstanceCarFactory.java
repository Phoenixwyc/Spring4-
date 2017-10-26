package cn.seu.edu.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * ʵ�������������ȴ������������ڵ��ù�����ʵ����������ʵ��
 * @author Administrator
 *
 */
public class InstanceCarFactory {
	
	private Map<String, Car> cars = null;
	
	public InstanceCarFactory() {
		cars = new HashMap<>();
		cars.put("Audi", new Car("Audi", 300000));
		cars.put("Ford", new Car("Ford", 200000));
	}
	
	public Car getCar(String brand) {
		return cars.get(brand);
	}

}
