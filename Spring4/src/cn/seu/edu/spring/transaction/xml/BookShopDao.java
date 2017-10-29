package cn.seu.edu.spring.transaction.xml;

public interface BookShopDao {
	
	// 根据isbn获取书的数量
	public int findBookNumsByIsbn(String isbn);
	//根据书号获取数的单价
	public int findBookPriceByIsbn(String isbn);
	
	// 更新书的库存，使isbn对应的书库存减去nums
	public void updateBookStock(String isbn, int nums);
	
	//更新用户账户余额，使username的balance减去price
	public void updateUserAccount(String username, int price);
	
	// 根据用户姓名查看余额
	public int findUserBalanceByUsername(String username);

}
