package cn.seu.edu.annotation.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.seu.edu.annotation.TestObject;

@Repository
public class UserRespositoryImpl implements UserRespository{
	
	@Autowired(required = false)
	private TestObject testObject;

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("UserRespositoryImpl save .....");
		System.out.println(testObject);
	}

}
