package cn.seu.edu.annotation.respository;

import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRespository implements UserRespository{

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("UserJdbcRespository save.....");
	}

}
