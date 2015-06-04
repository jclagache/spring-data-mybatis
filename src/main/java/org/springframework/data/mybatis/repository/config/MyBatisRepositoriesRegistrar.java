package org.springframework.data.mybatis.repository.config;

import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

/**
 * {@link ImportBeanDefinitionRegistrar} to enable {@link EnableMyBatisRepositories} annotation.
 * 
 * @author Jean-Christophe Lagache
 */
public class MyBatisRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return EnableMyBatisRepositories.class;
	}

	@Override
	protected RepositoryConfigurationExtension getExtension() {
		return new MyBatisRepositoryConfigExtension();
	}

}
