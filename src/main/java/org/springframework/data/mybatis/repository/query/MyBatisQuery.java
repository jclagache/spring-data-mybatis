package org.springframework.data.mybatis.repository.query;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * Binds spring Repository methods with mybatis mapper
 */
public class MyBatisQuery implements RepositoryQuery {
	
	private final MyBatisQueryMethod queryMethod;
	private final SqlSessionTemplate sessionTemplate;	
	
	public MyBatisQuery(MyBatisQueryMethod queryMethod, SqlSessionTemplate sessionTemplate) {
		Assert.notNull(queryMethod);
		Assert.notNull(sessionTemplate);
		this.queryMethod = queryMethod;
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public Object execute(Object[] parameters) {
		return ReflectionUtils
				.invokeMethod(queryMethod.getMethod(), sessionTemplate.getMapper(queryMethod.getRepositoryInterface()), parameters);
	}

	@Override
	public QueryMethod getQueryMethod() {		
		return queryMethod;
	}
}
