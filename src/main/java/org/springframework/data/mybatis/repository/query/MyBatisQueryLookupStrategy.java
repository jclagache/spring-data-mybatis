package org.springframework.data.mybatis.repository.query;

import java.lang.reflect.Method;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.repository.query.RepositoryQuery;

public class MyBatisQueryLookupStrategy {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private MyBatisQueryLookupStrategy() {
	}
	
	/**
	 * {@link QueryLookupStrategy} that tries to detect a declared query declared via {@link Query} annotation followed by
	 * a JPA named query lookup.
	 * 
	 * @author Oliver Gierke
	 */
	private static class DeclaredQueryLookupStrategy implements QueryLookupStrategy {
		
		private final SqlSessionTemplate sessionTemplate;

		public DeclaredQueryLookupStrategy(SqlSessionTemplate sessionTemplate) {
			this.sessionTemplate = sessionTemplate;
		}

		@Override
		public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, NamedQueries namedQueries) {
			return resolveQuery(new MyBatisQueryMethod(method, metadata), sessionTemplate, namedQueries);
		}
				
		protected RepositoryQuery resolveQuery(MyBatisQueryMethod method, SqlSessionTemplate sessionTemplate, NamedQueries namedQueries) {
			return new MyBatisQuery(method, sessionTemplate);			
		}
	}
	
	
	/**
	 * Creates a {@link QueryLookupStrategy} for the given {@link SqlSessionTemplate} and {@link Key}.
	 * 
	 * @param sessionTemplate
	 * @param key
	 * @return
	 */
	public static QueryLookupStrategy create(SqlSessionTemplate sessionTemplate, Key key) {
		if (key == null) {
			return new DeclaredQueryLookupStrategy(sessionTemplate);
		}
		if(Key.USE_DECLARED_QUERY.equals(key)) {
			return new DeclaredQueryLookupStrategy(sessionTemplate);
		}
		else {
			throw new IllegalArgumentException(String.format("Unsupported query lookup strategy %s!", key));
		}
	}
	
}
