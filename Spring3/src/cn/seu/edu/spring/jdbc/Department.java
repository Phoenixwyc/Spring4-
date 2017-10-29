package cn.seu.edu.spring.jdbc;

public class Department {
	private Integer id;
	private String dept_name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	@Override
	public String toString() {
		return "Departments [id=" + id + ", dept_name=" + dept_name + "]";
	}
	
	 
}
