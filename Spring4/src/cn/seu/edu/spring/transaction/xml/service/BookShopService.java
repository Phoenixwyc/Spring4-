package cn.seu.edu.spring.transaction.xml.service;

public interface BookShopService {
	
	// username用户买了 nums本书号为isbn的书
	public void purchase(String username, String isbn, int nums);
}
