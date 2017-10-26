package cn.seu.edu.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：先创建工厂本身，在调用工厂的实例方法返回实例
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
