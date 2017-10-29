package cn.seu.edu.spring.transaction;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionTest {
	private ApplicationContext ctx = null;
	private DataSource dataSource = null;
	private BookShopDao bookShopDao= null;
	private JdbcTemplate jdbcTemplate = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("application-context.xml");
		dataSource = ctx.getBean("dataSource", DataSource.class);
		bookShopDao = ctx.getBean("bookShopDao", BookShopDao.class);
		jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		bookShopService = ctx.getBean("bookShopService", BookShopService.class);
		cashier = ctx.getBean("cashier", Cashier.class);
		
	}
	
	@Before
	public void initData() {
		String sql1 = "update book_stock set stock = 4 where isbn = '1001'";
		jdbcTemplate.update(sql1);
		String sql2 = "update book_stock set stock = 8 where isbn = '1002'";
		jdbcTemplate.update(sql2);
		String sql3 = "update user_account set balance = 170 where username = 'AA'";
		jdbcTemplate.update(sql3);
	}
	
	@Test
	public void testBookShopDaoFindBookPriceByIsbn() {
		int result = bookShopDao.findBookPriceByIsbn("1001");
		Assert.assertEquals(100, result);
	}
	
	@Test
	public void testBookShopDaoUpdateBookStock() {
		bookShopDao.updateBookStock("1001", 1);
		int result = bookShopDao.findBookNumsByIsbn("1001");
		Assert.assertEquals(3, result);
	}
	
	
	@Test(expected = BookSockException.class)
	public void testBookShopDaoUpdateBookStockWithoutEnoughStock() {
		bookShopDao.updateBookStock("1001", 10);
		int result = bookShopDao.findBookNumsByIsbn("1001");
		Assert.assertEquals(3, result);
	}
	@Test
	public void testBookShopDaoFindBookNumsByIsbn() {
		int result = bookShopDao.findBookNumsByIsbn("1001");
		Assert.assertEquals(4, result);
	}
	
	@Test
	public void testBookShopDaoUpdateUserAccount() {
		bookShopDao.updateUserAccount("AA", 60);
		int result = bookShopDao.findUserBalanceByUsername("AA");
		Assert.assertEquals(110, result);
	}
	
	@Test(expected = UserAccountException.class)
	public void testBookShopDaoUpdateUserAccountWithoutEnoughBlance() {
		bookShopDao.updateUserAccount("AA", 200);
	}
	
	@Test
	public void testBookShopDaoFindUserBalance() {
		int result = bookShopDao.findUserBalanceByUsername("AA");
		Assert.assertEquals(170, result);
	}
	
	@Test
	public void testBookShopServicePurchaseNormal() {
		String username = "AA";
		String isbn = "1001";
		int nums = 1;
		bookShopService.purchase("AA", isbn, nums);
		int balance = bookShopDao.findUserBalanceByUsername(username);
		Assert.assertEquals(70, balance);
		int bookSock = bookShopDao.findBookNumsByIsbn(isbn);
		Assert.assertEquals(3, bookSock);
	}
	
//	@Test
//	public void testBookShopServicePurchaseAbnormalWithoutTransaction() {
//		String username = "AA";
//		String isbn = "1001";
//		int nums = 2;
//		try {
//			bookShopService.purchase("AA", isbn, nums);
//		} catch(UserAccountException e) {
//			// 注意这里，没有事务控制、库存足够、用户余额不足时，出现余额不变、库存减num的情况
//			int bookSock = bookShopDao.findBookNumsByIsbn(isbn);
//			Assert.assertEquals(2, bookSock);
//			int balance = bookShopDao.findUserBalanceByUsername(username);
//			Assert.assertEquals(160, balance);
//			fail(e.getMessage());
//		}
//	}
	
	@Test
	public void testBookShopServicePurchaseAbnormalWithTransaction() {
		String username = "AA";
		String isbn = "1001";
		int nums = 2;
		try {
			bookShopService.purchase("AA", isbn, nums);
		} catch(UserAccountException e) {
			// 注意这里，有事务控制、库存足够、用户余额不足时不会出现余额不变、库存减num的情况
			int bookSock = bookShopDao.findBookNumsByIsbn(isbn);
			Assert.assertEquals(4, bookSock);
			int balance = bookShopDao.findUserBalanceByUsername(username);
			Assert.assertEquals(170, balance);
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testTransactionalPropagation() {
		String username = "AA";
		List<String> isbns = Arrays.asList(new String[]{"1001", "1002"});
		cashier.checkout(username, isbns);
		
		int balance = bookShopDao.findUserBalanceByUsername(username);
		Assert.assertEquals(0, balance);
		
		int stock_1001 = bookShopDao.findBookNumsByIsbn(isbns.get(0));
		Assert.assertEquals(3, stock_1001);
		
		int stock_1002 = bookShopDao.findBookNumsByIsbn(isbns.get(1));
		Assert.assertEquals(7, stock_1002);
		
	}
	
	@Test
	public void testDataSource() throws SQLException {
		Assert.assertNotNull(dataSource.getConnection());
		System.out.println(dataSource.getConnection());
	}

}
