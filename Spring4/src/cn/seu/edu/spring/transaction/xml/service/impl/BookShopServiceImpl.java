package cn.seu.edu.spring.transaction.xml.service.impl;

import cn.seu.edu.spring.transaction.xml.BookShopDao;
import cn.seu.edu.spring.transaction.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService{
	
	private BookShopDao bookShopDao;
	
	@Override
	public void purchase(String username, String isbn, int nums) {
		// TODO Auto-generated method stub
		// ��һ����ȡ��ĵ���
		int bookPrice = bookShopDao.findBookPriceByIsbn(isbn);
		// �ڶ��� �������Ŀ��
		bookShopDao.updateBookStock(isbn, nums);
		// �����������û����
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
