package cn.seu.edu.spring.transaction;

import java.util.List;

public interface Cashier {

	// username������౾��isbns
	public void checkout(String username, List<String> isbns);
}
