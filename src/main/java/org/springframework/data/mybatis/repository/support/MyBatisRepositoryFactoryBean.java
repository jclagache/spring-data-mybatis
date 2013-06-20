package org.springframework.data.mybatis.repository.support;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

public class MyBatisRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends RepositoryFactoryBeanSupport<T, S, ID> {

	SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory() {		
		return new MyBatisRepositoryFactory(sqlSessionTemplate);
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(sqlSessionTemplate, "SqlSessionTemplate must not be null!");
		super.afterPropertiesSet();
	}

}
