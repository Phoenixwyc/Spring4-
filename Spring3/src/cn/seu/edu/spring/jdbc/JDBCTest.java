package cn.seu.edu.spring.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import junit.framework.Assert;

public class JDBCTest {
	
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate = null;
	private EmployeeDao employeeDao = null;
	private DepartmentDao departmentDao = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("application-context.xml");
		jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		employeeDao = ctx.getBean("employeeDao", EmployeeDao.class);
		departmentDao = ctx.getBean("departmentDao", DepartmentDao.class);
		namedParameterJdbcTemplate = ctx.getBean("namedParameterJdbcTemplate", NamedParameterJdbcTemplate.class);
	}
	
	@Test
	public void testUpdate() {
		String sql = "update employees set last_name = ? where id = ?";
		int result = jdbcTemplate.update(sql, "Phoenix", "5");
		assertEquals(1, result);
	}
	
	/**
	 * 执行批量更新 insert、delete、update
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "insert into employees(last_name, email, dept_id) values(?, ?, ?)";
		List<Object[]> args = new ArrayList<>();
		args.add(new Object[]{"A", "A@163.emmail", "1"});
		args.add(new Object[]{"B", "B@163.emmail", "2"});
		args.add(new Object[]{"C", "C@163.emmail", "3"});
		args.add(new Object[]{"D", "D@163.emmail", "3"});
		jdbcTemplate.batchUpdate(sql, args);
	}
	
	/**
	 * 查找指定对象
	 * 注意这里不能使用 queryForObject(String sql, Class<Employee> requiredType, Object... args) 
	 * 应该使用 queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1. RowMapper 的作用就是实现结果集行与对象之间的映射，常用的实现类为BeanPropertyRowMapper
	 * 2. 在 SQL 中使用列的别名实现列名和类属性名之间的映射(这个不是必须的)
	 * 3. JdbcTemplate只相当于JDBC的工具类，不是ORM框架，不支持级联属性
	 */
	@Test
	public void testQueryForObject() {
		
		String sql = "select email, id, last_name, dept_id as \"departments\" from employees where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}
	
	/**
	 * 查询一组对象，这里用的不是queryForList
	 */
	@Test
	public void testQueryForList() {
		String sql = "select * from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper, 2);
		System.out.println(list);
	}
	
	/**
	 * 产需单列的值或者做统计查询
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "select count(id) from departments";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		assertEquals(4, count);
	}
	

	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.get(1));
	}
	
	@Test
	public void testDepartmentDao() {
		System.out.println(departmentDao.get(1));
	}
	
	/**
	 * 测试NamedParemeterJdbcTemplate，其特点是可以为参数起名字
	 * 在有多个参数的情况下，通过参数名进行设置而不是传统的基于位置的设置方式
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		// 注意这里的参数命名是任意的
		String sql = "insert into employees(last_name, email, dept_id) values(:lastName, :email, :dept_no)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastName", "Phoenix");
		paramMap.put("email", "Phoenix@163.com");
		paramMap.put("dept_no", 1);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2() {
		// 如果参数的命名方式恰好和对应POJO的属性一致的话，就可以使用SqlParameterSource，常用的实现类为BeanPropertySqlParameterSource，类似于ORM框架，直接存储一个对象
		String sql = "insert into employees(last_name, email, dept_id) values(:lastName, :email, :deptId)";
		// 创建对象
		Employee employee = new Employee();
		employee.setLastName("WYCPhoenix");
		employee.setEmail("WYCPhoenix@163.com");
		employee.setDeptId(2); 
		
		// 创建ParameterSource
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		// 保存对象
		int result = namedParameterJdbcTemplate.update(sql, paramSource);
		Assert.assertEquals(1, result);
	}
	
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
		assertNotNull(dataSource);
		System.out.println(dataSource.getConnection());
	}

}
