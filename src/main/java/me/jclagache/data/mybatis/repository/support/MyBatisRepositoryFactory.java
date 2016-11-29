package me.jclagache.data.mybatis.repository.support;

import me.jclagache.data.mybatis.repository.MyBatisRepository;
import me.jclagache.data.mybatis.repository.core.mapping.MyBatisPersistentEntity;
import me.jclagache.data.mybatis.repository.core.mapping.MyBatisPersistentProperty;
import me.jclagache.data.mybatis.repository.query.MyBatisQueryLookupStrategy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.mapping.context.MappingContext;
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
	private final MappingContext<? extends MyBatisPersistentEntity<?>,
			MyBatisPersistentProperty> context;
	
	public MyBatisRepositoryFactory(SqlSessionTemplate sessionTemplate, MappingContext<? extends MyBatisPersistentEntity<?>,
                MyBatisPersistentProperty> context) {
		super();
		Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null!");
		Assert.notNull(context, "MappingContext must not be null!");
		this.sessionTemplate = sessionTemplate;
		this.context = context;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T, ID extends Serializable> MyBatisEntityInformation<T, ID> getEntityInformation(
			Class<T> domainClass) {
		MyBatisPersistentEntity<T> entity = (MyBatisPersistentEntity<T>) context.getPersistentEntity(domainClass);
		return new MappingMyBatisEntityInformation<T,ID>(entity);
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
