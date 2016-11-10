package me.jclagache.data.mybatis.repository.config;

import me.jclagache.data.mybatis.repository.core.mapping.SimpleMyBatisMappingContext;
import me.jclagache.data.mybatis.repository.support.MyBatisRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.data.repository.config.XmlRepositoryConfigurationSource;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


/**
 * Implementation of {@link org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport} for supporting mybatis
 * allows to use read EnableMyBatisRepositories annotation for configuring repository bean
 *
 */
public class MyBatisRepositoryConfigExtension extends RepositoryConfigurationExtensionSupport {

	@Override
	public String getRepositoryFactoryClassName() {
		return MyBatisRepositoryFactoryBean.class.getName();
	}

	@Override
	protected String getModulePrefix() {
		return "mybatis";
	}

	/*
     * (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#registerBeansForRoot(org.springframework.beans.factory.support.BeanDefinitionRegistry, org.springframework.data.repository.config.RepositoryConfigurationSource)
     */
	@Override
	public void registerBeansForRoot(BeanDefinitionRegistry registry, RepositoryConfigurationSource configurationSource) {

		super.registerBeansForRoot(registry, configurationSource);

		String attribute = configurationSource.getAttribute("mappingContextRef");

		if (!StringUtils.hasText(attribute)) {
			registry.registerBeanDefinition(String.format("%1$s.%2$s", SimpleMyBatisMappingContext.class.getName(), "DEFAULT"),
					new RootBeanDefinition(SimpleMyBatisMappingContext.class));
		}
	}

	@Override
	public void postProcess(BeanDefinitionBuilder builder, XmlRepositoryConfigurationSource config) {

		Element element = config.getElement();

		postProcess(builder, element.getAttribute("transaction-manager-ref"),
				element.getAttribute("sql-session-template-ref"), element.getAttribute("mapping-context-ref"), config.getSource());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#postProcess(org.springframework.beans.factory.support.BeanDefinitionBuilder, org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource)
	 */
	@Override
	public void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {

		AnnotationAttributes attributes = config.getAttributes();

		postProcess(builder, attributes.getString("transactionManagerRef"),
				attributes.getString("sqlSessionTemplateRef"), attributes.getString("mappingContextRef"), config.getSource());
	}

	private void postProcess(BeanDefinitionBuilder builder, String transactionManagerRef, String sqlSessionTemplateRef, String mappingContextRef,
			Object source) {

		//TODO : transactionManagerRef

		if (StringUtils.hasText(sqlSessionTemplateRef)) {
			builder.addPropertyReference("sqlSessionTemplate", sqlSessionTemplateRef);
		}
		else {
			builder.addPropertyReference("sqlSessionTemplate","sqlSessionTemplate");
		}
		if (StringUtils.hasText(mappingContextRef)) {
			builder.addPropertyReference("myBatisMappingContext", mappingContextRef);
		}
		else {
			builder.addPropertyReference("myBatisMappingContext",String.format("%1$s.%2$s", SimpleMyBatisMappingContext.class.getName(), "DEFAULT"));
		}

	}



}
