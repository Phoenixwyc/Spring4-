package cn.seu.edu.annotation.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.seu.edu.annotation.respository.UserRespository;

@Service
public class UserService {
	
	
	private UserRespository userRespository;
	
	@Autowired
	//@Qualifier(value = "userRespositoryImpl")
	public void setUserRespository(@Qualifier(value = "userRespositoryImpl") UserRespository userRespository) {
		this.userRespository = userRespository;
	}



	public void add() {
		System.out.println("UserSerivce add.....");
		userRespository.save();
	}

}
