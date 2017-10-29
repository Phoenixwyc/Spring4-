package cn.seu.edu.spring.transaction.xml.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.seu.edu.spring.transaction.xml.service.BookShopService;
import cn.seu.edu.spring.transaction.xml.service.Cashier;

public class CashierImpl implements Cashier{
	
	private BookShopService bookShopService;

	@Override
	public void checkout(String username, List<String> isbns) {
		// TODO Auto-generated method stub
		for (String isbn : isbns) {
			// purchase也是一个事务方法
			bookShopService.purchase(username, isbn, 1);
		}
	}

	public BookShopService getBookShopService() {
		return bookShopService;
	}

	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	

}
