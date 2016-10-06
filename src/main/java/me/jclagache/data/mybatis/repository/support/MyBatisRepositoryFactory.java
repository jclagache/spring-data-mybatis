package me.jclagache.data.mybatis.repository.support;

import org.mybatis.spring.SqlSessionTemplate;
import me.jclagache.data.mybatis.repository.MyBatisRepository;
import me.jclagache.data.mybatis.repository.query.MyBatisQueryLookupStrategy;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.util.Assert;

import java.io.Serializable;

public class MyBatisRepositoryFactory extends RepositoryFactorySupport {
	
	private final SqlSessionTemplate sessionTemplate;
	
	public MyBatisRepositoryFactory(SqlSessionTemplate sessionTemplate) {
		super();
		Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null!");		
		this.sessionTemplate = sessionTemplate;			
	}

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(
			Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getTargetRepository(RepositoryInformation repositoryInformation) {
		return new SimpleMyBatisRepository(sessionTemplate, repositoryInformation.getRepositoryInterface().getCanonicalName());
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
		return MyBatisRepository.class;
	}


	@Override
	protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
		return MyBatisQueryLookupStrategy.create(sessionTemplate, key);
	}


}
