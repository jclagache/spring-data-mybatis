package org.springframework.data.mybatis.repository.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
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

@Configuration
@EnableTransactionManagement
public class InfrastructureConfig implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Bean
	@Autowired
	public MapperScannerConfigurer mapperScannerConfigurer(@Value("${mybatis.mapper.base.package}") String basePackage) {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setMarkerInterface(MyBatisRepository.class);
		mapperScannerConfigurer.setBasePackage(basePackage);
		return mapperScannerConfigurer;
	}

	@Bean
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("org.springframework.data.mybatis.domain");
		sessionFactory.setMapperLocations(getResources("classpath*:mapper/**/*.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	@Autowired
	PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	@Autowired
	SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		mapperScannerConfigurer.setBasePackage("org.springframework.data.mybatis.repository");
		return mapperScannerConfigurer;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private Resource[] getResources(String packagePath) throws IOException {
		ResourcePatternResolver resourceResolver = ResourcePatternUtils
				.getResourcePatternResolver(resourceLoader);
		return resourceResolver.getResources(packagePath);
	}

}
