package me.jclagache.data.mybatis.repository.core.mapping;

import org.springframework.data.mapping.PersistentEntity;

public interface MyBatisPersistentEntity<T> extends PersistentEntity<T, MyBatisPersistentProperty> {
}
