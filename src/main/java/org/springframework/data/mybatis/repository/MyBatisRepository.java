package org.springframework.data.mybatis.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * MyBatis specific extension of {@link org.springframework.data.repository.Repository}.
 * 
 * @author Jean-Christophe Lagache
 */
@NoRepositoryBean
public interface MyBatisRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	
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
	

}
