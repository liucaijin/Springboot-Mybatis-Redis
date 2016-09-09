package com.liucaijin.core.datasource;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class SessionFactoryConfig {
	 /**     * mybatis 配置路径     */   
   private static String MYBATIS_CONFIG="mybatis-config.xml";
   /**     * mybatis mapper resource 路径     */
   private static String MAPPER_PATH="/mapper/**.xml";
   
   @Autowired
   private DataSource dataSource;
   
   private String typeAliasPackage="com.liucaijin.domain";
   
   /** 
    *创建sqlSessionFactoryBean 实例     
    * 并且设置configtion 如驼峰命名.等等     
    * 设置mapper 映射路径     
    * 设置datasource数据源     
    * @return     
    */
   
   @Bean
   public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception{
	   
	   SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
	   sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
	   /**
	    * 添加mapper扫描路径
	    */
	   PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver=new PathMatchingResourcePatternResolver();
	   String packageSearchPath =ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+MAPPER_PATH;
	   sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
	   
	   sqlSessionFactoryBean.setDataSource(dataSource);
	   
//	   sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
	   
	   return sqlSessionFactoryBean;
   }
   
   
}
