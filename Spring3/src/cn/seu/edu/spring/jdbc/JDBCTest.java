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
	 * ִ���������� insert��delete��update
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
	 * ����ָ������
	 * ע�����ﲻ��ʹ�� queryForObject(String sql, Class<Employee> requiredType, Object... args) 
	 * Ӧ��ʹ�� queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1. RowMapper �����þ���ʵ�ֽ�����������֮���ӳ�䣬���õ�ʵ����ΪBeanPropertyRowMapper
	 * 2. �� SQL ��ʹ���еı���ʵ����������������֮���ӳ��(������Ǳ����)
	 * 3. JdbcTemplateֻ�൱��JDBC�Ĺ����࣬����ORM��ܣ���֧�ּ�������
	 */
	@Test
	public void testQueryForObject() {
		
		String sql = "select email, id, last_name, dept_id as \"departments\" from employees where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}
	
	/**
	 * ��ѯһ����������õĲ���queryForList
	 */
	@Test
	public void testQueryForList() {
		String sql = "select * from employees where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> list = jdbcTemplate.query(sql, rowMapper, 2);
		System.out.println(list);
	}
	
	/**
	 * ���赥�е�ֵ������ͳ�Ʋ�ѯ
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
	 * ����NamedParemeterJdbcTemplate�����ص��ǿ���Ϊ����������
	 * ���ж������������£�ͨ���������������ö����Ǵ�ͳ�Ļ���λ�õ����÷�ʽ
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		// ע������Ĳ��������������
		String sql = "insert into employees(last_name, email, dept_id) values(:lastName, :email, :dept_no)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastName", "Phoenix");
		paramMap.put("email", "Phoenix@163.com");
		paramMap.put("dept_no", 1);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2() {
		// ���������������ʽǡ�úͶ�ӦPOJO������һ�µĻ����Ϳ���ʹ��SqlParameterSource�����õ�ʵ����ΪBeanPropertySqlParameterSource��������ORM��ܣ�ֱ�Ӵ洢һ������
		String sql = "insert into employees(last_name, email, dept_id) values(:lastName, :email, :deptId)";
		// ��������
		Employee employee = new Employee();
		employee.setLastName("WYCPhoenix");
		employee.setEmail("WYCPhoenix@163.com");
		employee.setDeptId(2); 
		
		// ����ParameterSource
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		// �������
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
