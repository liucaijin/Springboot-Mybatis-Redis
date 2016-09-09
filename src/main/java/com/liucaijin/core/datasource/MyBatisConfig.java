package com.liucaijin.core.datasource;

 
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@MapperScan("com.liucaijin.dao")
public class MyBatisConfig {
	@Autowired
	private JdbcConfig jdbcConfig;
	
	@Bean
    public javax.sql.DataSource createDataSource() throws Exception{
    	return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
    			.driverClassName(jdbcConfig.getDriverclass())
    			.url(jdbcConfig.getUrl())
    			.username(jdbcConfig.getUsername())
    			.password(jdbcConfig.getUserpassword())
    			.build();
    }
}

@PropertySource(value = { "application.properties" })
@Component
class JdbcConfig {
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String userpassword;
	@Value("${spring.datasource.driverClassName}")
	private String driverclass;
	@Value("${spring.datasource.url}")
	private String url;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getDriverclass() {
		return driverclass;
	}

	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}