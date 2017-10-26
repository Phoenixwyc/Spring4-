package cn.seu.edu.lifecycle;


public class Car {
	
	public Car() {
		System.out.println("Car's Constructor");
	}
	
	private String brand;
	
	public void setBrand(String brand) {
		System.out.println("setBrand");
		this.brand = brand;
	}
	
	public void init() {
		System.out.println("init.....");
	}
	
	public void destory() {
		System.out.println("destory.....");
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + "]";
	}
	
	

}
