package me.jclagache.data.mybatis.repository.core.mapping;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class SimpleMyBatisMappingContext extends
        AbstractMappingContext<SimpleMyBatisPersistentEntity<?>, MyBatisPersistentProperty> implements ApplicationContextAware {

    /**
     * Contains the application context to configure the application.
     */
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    protected <T> SimpleMyBatisPersistentEntity<?> createPersistentEntity(TypeInformation<T> typeInformation) {
        SimpleMyBatisPersistentEntity<T> entity = new SimpleMyBatisPersistentEntity<>(typeInformation);
        if (context != null) {
            entity.setApplicationContext(context);
        }
        return entity;

    }

    @Override
    protected MyBatisPersistentProperty createPersistentProperty(Field field, PropertyDescriptor descriptor, SimpleMyBatisPersistentEntity<?> owner, SimpleTypeHolder simpleTypeHolder) {
        return new SimpleMyBatisPersistentProperty(field, descriptor, owner, simpleTypeHolder);
    }
}
