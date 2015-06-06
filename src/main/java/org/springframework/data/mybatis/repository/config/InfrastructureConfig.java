package org.springframework.data.mybatis.repository.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.data.mybatis.repository.MyBatisRepository;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 *
 *Configuration of Mybats:
 *
 * SqlSessionFactory see: https://mybatis.github.io/mybatis-3/getting-started.html
 * MapperScannerConfigurer see: https://mybatis.github.io/spring/mappers.html#scan
 * SqlSessionTemplate see https://mybatis.github.io/spring/sqlsession.html
 * Spring TransactionManager
 *
 */
@Configuration
@EnableTransactionManagement
public class InfrastructureConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(@Value("${mybatis.mapper.base.package:*}") String basePackage) {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setMarkerInterface(MyBatisRepository.class);
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		mapperScannerConfigurer.setBasePackage(basePackage);
		return mapperScannerConfigurer;
	}

	@Bean
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, @Value("${mybatis.aliases.package:}") String aliases) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage(aliases);
		sessionFactory.setMapperLocations(getResources(resourceLoader, "classpath*:mapper/**/*.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	@Autowired
	SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Autowired
	PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * Method which loads resources by packagePath
	 * @param resourceLoader
	 * @param packagePath
	 * @return
	 * @throws IOException
	 */
	private Resource[] getResources(ResourceLoader resourceLoader, String packagePath) throws IOException {
		ResourcePatternResolver resourceResolver = ResourcePatternUtils
				.getResourcePatternResolver(resourceLoader);
		return resourceResolver.getResources(packagePath);
	}

}
