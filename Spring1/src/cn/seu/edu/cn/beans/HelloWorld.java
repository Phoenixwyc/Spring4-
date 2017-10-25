package cn.seu.edu.cn.beans;


public class HelloWorld {
	private String name;
	
	public void hello() {
		System.out.println("hello" + name);
	}
	
	public void setName(String name) {
		System.out.println("setName");
		this.name = name;
	}
	
	public HelloWorld() {
		// TODO Auto-generated constructor stub
		System.out.println("Constructor");
	}

}
