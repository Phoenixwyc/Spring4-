package cn.seu.edu.spring.transaction;

public interface BookShopService {
	
	// username�û����� nums�����Ϊisbn����
	public void purchase(String username, String isbn, int nums);
}
