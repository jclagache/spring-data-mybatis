package me.jclagache.data.mybatis.repository.support;

import org.springframework.data.repository.core.EntityInformation;

import java.io.Serializable;

/**
 * Marker interface for the MyBatis Entity Information.
 *
 * @author Jean-Christophe Lagache
 */
public interface MyBatisEntityInformation<T, ID extends Serializable> extends EntityInformation<T, ID> {
}
