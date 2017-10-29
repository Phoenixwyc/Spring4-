package cn.seu.edu.spring.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "cashier")
public class CashierImpl implements Cashier{
	
	@Autowired
	private BookShopService bookShopService;

	@Transactional
	@Override
	public void checkout(String username, List<String> isbns) {
		// TODO Auto-generated method stub
		for (String isbn : isbns) {
			// purchase也是一个事务方法
			bookShopService.purchase(username, isbn, 1);
		}
	}

}
