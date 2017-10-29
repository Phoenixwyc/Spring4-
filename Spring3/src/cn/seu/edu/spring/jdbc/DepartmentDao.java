package cn.seu.edu.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 不推荐使用JdbcDaoSupport，直接使用JdbcTemplate即可
 * @author Administrator
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport{

	/**
	 * 这里采用了折中的方式，因为查看JdbcDaoSupport发现setDataSource是final的
	 * 不能直接重写
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
