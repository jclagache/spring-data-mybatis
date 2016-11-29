package me.jclagache.data.mybatis.repository.support;

import me.jclagache.data.mybatis.repository.core.mapping.MyBatisPersistentEntity;
import org.springframework.data.repository.core.support.PersistentEntityInformation;

import java.io.Serializable;

/**
 * Created by jclagache on 09/11/2016.
 */
public class MappingMyBatisEntityInformation<T, ID extends Serializable> extends PersistentEntityInformation<T, ID> implements MyBatisEntityInformation<T, ID>{

    private final MyBatisPersistentEntity<T> entity;

    /**
     * Creates a new {@link MappingMyBatisEntityInformation} for the given {@link MyBatisPersistentEntity}.
     *
     * @param entity must not be {@literal null}.
     */
    public MappingMyBatisEntityInformation(MyBatisPersistentEntity<T> entity) {
        super(entity);
        this.entity = entity;
    }
}
