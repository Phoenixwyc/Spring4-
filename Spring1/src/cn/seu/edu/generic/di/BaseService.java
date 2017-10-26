package cn.seu.edu.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {
	
	@Autowired
	protected BaseRespository<T> respository;
	
	public void add() {
		System.out.println("add.....");
		System.out.println(respository);
	}

}
