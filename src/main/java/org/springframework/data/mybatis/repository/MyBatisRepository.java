package org.springframework.data.mybatis.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * MyBatis specific extension of {@link org.springframework.data.repository.Repository}.
 * 
 * @author Jean-Christophe Lagache
 */
@NoRepositoryBean
public interface MyBatisRepository<T, ID extends Serializable> extends Repository<T, ID> {
	
	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	T findOne(ID id);
	
	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	List<T> findAll();
	
	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	boolean exists(ID id);
	
	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();
	

}
