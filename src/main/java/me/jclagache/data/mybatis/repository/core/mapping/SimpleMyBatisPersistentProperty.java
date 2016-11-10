package me.jclagache.data.mybatis.repository.core.mapping;

import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.AnnotationBasedPersistentProperty;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class SimpleMyBatisPersistentProperty extends AnnotationBasedPersistentProperty<MyBatisPersistentProperty>
        implements MyBatisPersistentProperty{

    /**
     * Creates a new {@link AnnotationBasedPersistentProperty}.
     *
     * @param field              must not be {@literal null}.
     * @param propertyDescriptor can be {@literal null}.
     * @param owner              must not be {@literal null}.
     * @param simpleTypeHolder
     */
    public SimpleMyBatisPersistentProperty(Field field, PropertyDescriptor propertyDescriptor, PersistentEntity<?, MyBatisPersistentProperty> owner, SimpleTypeHolder simpleTypeHolder) {
        super(field, propertyDescriptor, owner, simpleTypeHolder);
    }

    @Override
    protected Association<MyBatisPersistentProperty> createAssociation() {
        return new Association<MyBatisPersistentProperty>(this, null);
    }
}
