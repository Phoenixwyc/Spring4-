package cn.seu.edu.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "bookShopDao")
public class BookShopDaoImpl implements BookShopDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findBookPriceByIsbn(String isbn) {
		// TODO Auto-generated method stub
		String sql = "select price from book where isbn = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return result;
	}

	@Override
	public void updateBookStock(String isbn, int nums) {
		// TODO Auto-generated method stub
		// 检查书的库存异常，不够则抛出异常
		// 虽然MySql在建表的时候使用了check，但是由于MySql并不支持检查约束，所以这里需要显式的检查
		int bookNums = this.findBookNumsByIsbn(isbn);
		if (bookNums < nums) {
			throw new BookSockException("库存不足");
		}
		String sql = "update book_stock set stock = stock - ? where isbn = ?";
		jdbcTemplate.update(sql, nums, isbn);
	}

	@Override
	public void updateUserAccount(String username, int price) {
		// TODO Auto-generated method stub
		int userBalance = this.findUserBalanceByUsername(username);
		if (userBalance < price) {
			throw new UserAccountException("用户余额不足");
		}
		String sql = "update user_account set balance = balance - ? where username = ?";
		jdbcTemplate.update(sql, price, username);
	}

	@Override
	public int findBookNumsByIsbn(String isbn) {
		// TODO Auto-generated method stub
		String sql = "select stock from book_stock where isbn = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return result;
	}

	@Override
	public int findUserBalanceByUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "select balance from user_account where username = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, username);
		return result;
	}

}
