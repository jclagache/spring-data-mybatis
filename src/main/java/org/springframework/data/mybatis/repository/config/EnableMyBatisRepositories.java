package org.springframework.data.mybatis.repository.config;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Import;
import org.springframework.data.mybatis.repository.support.MyBatisRepositoryFactoryBean;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

import java.lang.annotation.*;

/**
 * Annotation to enable MyBatis repositories. Will scan the package of the
 * annotated configuration class for Spring Data repositories by default.
 * 
 * @author Jean-Christophe Lagache
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MyBatisRepositoriesRegistrar.class, InfrastructureConfig.class})
public @interface EnableMyBatisRepositories {
	/**
	 * Alias for the {@link #basePackages()} attribute. Allows for more concise
	 * annotation declarations e.g.:
	 * {@code @EnableMyBatisRepositories("org.my.pkg")} instead of
	 * {@code @EnableMyBatisRepositories(basePackages="org.my.pkg")}.
	 */
	String[] value() default {};

	/**
	 * Base packages to scan for annotated components. {@link #value()} is an
	 * alias for (and mutually exclusive with) this attribute. Use
	 * {@link #basePackageClasses()} for a type-safe alternative to String-based
	 * package names.
	 */
	String[] basePackages() default {};

	/**
	 * Type-safe alternative to {@link #basePackages()} for specifying the
	 * packages to scan for annotated components. The package of each class
	 * specified will be scanned. Consider creating a special no-op marker class
	 * or interface in each package that serves no purpose other than being
	 * referenced by this attribute.
	 */
	Class<?>[] basePackageClasses() default {};

	/**
	 * Specifies which types are eligible for component scanning. Further
	 * narrows the set of candidate components from everything in
	 * {@link #basePackages()} to everything in the base packages that matches
	 * the given filter or filters.
	 */
	Filter[] includeFilters() default {};

	/**
	 * Specifies which types are not eligible for component scanning.
	 */
	Filter[] excludeFilters() default {};

	/**
	 * Returns the postfix to be used when looking up custom repository
	 * implementations. Defaults to {@literal Impl}. So for a repository named
	 * {@code PersonRepository} the corresponding implementation class will be
	 * looked up scanning for {@code PersonRepositoryImpl}.
	 * 
	 * @return
	 */
	String repositoryImplementationPostfix() default "Impl";
	
	/**
	 * Configures the location of where to find the Spring Data named queries properties file. Will default to
	 * {@code META-INFO/jpa-named-queries.properties}.
	 * 
	 * @return
	 */
	String namedQueriesLocation() default "";

	/**
	 * Returns the key of the {@link QueryLookupStrategy} to be used for lookup
	 * queries for query methods. Defaults to {@link Key#USE_DECLARED_QUERY}.
	 * 
	 * @return
	 */
	Key queryLookupStrategy() default Key.USE_DECLARED_QUERY;

	/**
	 * Returns the {@link FactoryBean} class to be used for each repository
	 * instance. Defaults to {@link JpaRepositoryFactoryBean}.
	 * 
	 * @return
	 */
	Class<?> repositoryFactoryBeanClass() default MyBatisRepositoryFactoryBean.class;

	String sqlSessionTemplateRef() default "sqlSessionTemplate";

	/**
	 * /** Configures the name of the {@link PlatformTransactionManager} bean
	 * definition to be used to create repositories discovered through this
	 * annotation. Defaults to {@code transactionManager}.
	 * 
	 * @return
	 */
	String transactionManagerRef() default "transactionManager";
}
