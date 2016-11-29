package me.jclagache.data.mybatis.repository.support;

import me.jclagache.data.mybatis.repository.core.mapping.MyBatisPersistentEntity;
import me.jclagache.data.mybatis.repository.core.mapping.MyBatisPersistentProperty;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import java.io.Serializable;

public class MyBatisRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends RepositoryFactoryBeanSupport<T, S, ID> {

	private SqlSessionTemplate sqlSessionTemplate;
	private MappingContext<? extends MyBatisPersistentEntity<?>, MyBatisPersistentProperty> context;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setMyBatisMappingContext(MappingContext<? extends MyBatisPersistentEntity<?>, MyBatisPersistentProperty> mappingContext) {
		super.setMappingContext(mappingContext);
		this.context = mappingContext;
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory() {		
		return new MyBatisRepositoryFactory(sqlSessionTemplate, context);
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(sqlSessionTemplate, "SqlSessionTemplate must not be null!");
		Assert.notNull(context, "MyBatisMappingContext must not be null!");
		super.afterPropertiesSet();
	}


}
