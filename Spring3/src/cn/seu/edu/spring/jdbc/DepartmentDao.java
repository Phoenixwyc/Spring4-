package cn.seu.edu.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * ���Ƽ�ʹ��JdbcDaoSupport��ֱ��ʹ��JdbcTemplate����
 * @author Administrator
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport{

	/**
	 * ������������еķ�ʽ����Ϊ�鿴JdbcDaoSupport����setDataSource��final��
	 * ����ֱ����д
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public Department get(Integer id) {
		String sql = "select * from departments where id = ?";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		Department department= getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return department;
	}
}