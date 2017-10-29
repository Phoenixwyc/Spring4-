package cn.seu.edu.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "bookShopService")
public class BookShopServiceImpl implements BookShopService{
	
	@Autowired
	private BookShopDao bookShopDao;

	// 添加事务注解，这里事务传播行为采用默认的required，即使用调用方法的事务z,并发事务隔离级别为读已提交
	// Spring默认对所有事务的运行时异常回滚
//	@Transactional(propagation = Propagation.REQUIRED, 
//			isolation = Isolation.READ_COMMITTED,
//			noRollbackFor = {UserAccountException.class},
//			rollbackFor = {BookSockException.class},
//			readOnly = false,
//			timeout = 1)
	@Transactional
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

}
