package cn.seu.edu.spring.transaction.xml.service.impl;

import cn.seu.edu.spring.transaction.xml.BookShopDao;
import cn.seu.edu.spring.transaction.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService{
	
	private BookShopDao bookShopDao;
	
	@Override
	public void purchase(String username, String isbn, int nums) {
		// TODO Auto-generated method stub
		// 第一步获取书的单价
		int bookPrice = bookShopDao.findBookPriceByIsbn(isbn);
		// 第二步 更新数的库存
		bookShopDao.updateBookStock(isbn, nums);
		// 第三步更新用户余额
		int totalMoney = bookPrice * nums;
		bookShopDao.updateUserAccount(username, totalMoney);
	}

	public BookShopDao getBookShopDao() {
		return bookShopDao;
	}

	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	

}
