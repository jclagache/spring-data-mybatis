package org.springframework.data.mybatis.repository.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.mybatis.repository.MyBatisRepository;
import org.springframework.util.Assert;

public class SimpleMyBatisRepository<T, ID extends Serializable> implements MyBatisRepository<T, ID> {
	
	private SqlSessionTemplate sessionTemplate;
	private String mappedStatementId;
	
	public SimpleMyBatisRepository(SqlSessionTemplate sessionTemplate, String mappedStatementNamespace) {
		Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null!");
		Assert.notNull(mappedStatementNamespace, "mappedStatementNamespace must not be null!");
		this.sessionTemplate = sessionTemplate;
		this.mappedStatementId = mappedStatementNamespace + ".find";
	}
	
	@Override
	public T findOne(ID id) {
		Map<String, ID> params = new HashMap<String, ID>();
		params.put("pk", id);
		return sessionTemplate.selectOne(mappedStatementId, params);
	}

	@Override
	public List<T> findAll() {
		Map<String, ID> params = new HashMap<String, ID>();
		return sessionTemplate.selectList(mappedStatementId, params);
	}

	@Override
	public boolean exists(ID id) {
		return findOne(id) == null ? false : true;
	}

	@Override
	public long count() {
		return findAll().size();
	}
	
	

}
