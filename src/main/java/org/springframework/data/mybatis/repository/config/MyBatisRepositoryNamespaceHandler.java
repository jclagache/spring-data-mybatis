package org.springframework.data.mybatis.repository.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.data.repository.config.RepositoryBeanDefinitionParser;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

public class MyBatisRepositoryNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		RepositoryConfigurationExtension extension = new MyBatisRepositoryConfigExtension();
		RepositoryBeanDefinitionParser repositoryBeanDefinitionParser = new RepositoryBeanDefinitionParser(extension);
		registerBeanDefinitionParser("repositories", repositoryBeanDefinitionParser);
	}

}
