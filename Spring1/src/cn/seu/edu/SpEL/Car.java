package cn.seu.edu.SpEL;

public class Car {
	private String brand;
	private double price;
	
	// ¬÷Ã•÷‹≥§
	private double tyerPerimeter;
	
	public Car() {
		System.out.println("Constructor Car........");
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	
	
	public double getTyerPerimeter() {
		return tyerPerimeter;
	}

	public void setTyerPerimeter(double tyerPerimeter) {
		this.tyerPerimeter = tyerPerimeter;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", tyerPerimeter=" + tyerPerimeter + "]";
	}
	

	
	
	

}
