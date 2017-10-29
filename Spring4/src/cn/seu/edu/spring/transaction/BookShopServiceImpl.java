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

	// �������ע�⣬�������񴫲���Ϊ����Ĭ�ϵ�required����ʹ�õ��÷���������z,����������뼶��Ϊ�����ύ
	// SpringĬ�϶��������������ʱ�쳣�ع�
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
		// ��һ����ȡ��ĵ���
		int bookPrice = bookShopDao.findBookPriceByIsbn(isbn);
		// �ڶ��� �������Ŀ��
		bookShopDao.updateBookStock(isbn, nums);
		// �����������û����
		int totalMoney = bookPrice * nums;
		bookShopDao.updateUserAccount(username, totalMoney);
	}

}
