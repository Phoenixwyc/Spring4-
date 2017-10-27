package cn.seu.edu.properties;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	public static void main(String[] args) throws SQLException{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-property.xml");
		
		DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
		System.out.println(dataSource.getConnection());
				
	}

}
