package cn.seu.edu.spring.transaction.xml;

public interface BookShopDao {
	
	// ����isbn��ȡ�������
	public int findBookNumsByIsbn(String isbn);
	//������Ż�ȡ���ĵ���
	public int findBookPriceByIsbn(String isbn);
	
	// ������Ŀ�棬ʹisbn��Ӧ�������ȥnums
	public void updateBookStock(String isbn, int nums);
	
	//�����û��˻���ʹusername��balance��ȥprice
	public void updateUserAccount(String username, int price);
	
	// �����û������鿴���
	public int findUserBalanceByUsername(String username);

}
