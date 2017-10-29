package cn.seu.edu.spring.transaction.xml.service;

import java.util.List;

public interface Cashier {

	// username可以买多本书isbns
	public void checkout(String username, List<String> isbns);
}
