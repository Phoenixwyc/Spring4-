package cn.seu.edu.spring.transaction.xml.service;

import java.util.List;

public interface Cashier {

	// username������౾��isbns
	public void checkout(String username, List<String> isbns);
}
