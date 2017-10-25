package cn.seu.edu.cn.beans;

import java.util.Properties;

public class DataSource {
	private Properties properties;
	
	public DataSource(){}
	
	public DataSource(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "DataSource [properties=" + properties + "]";
	}
	
	

}
