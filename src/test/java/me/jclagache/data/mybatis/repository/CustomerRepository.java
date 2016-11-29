package me.jclagache.data.mybatis.repository;

import me.jclagache.data.mybatis.domain.Customer;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Mapping and queries defined in mapper/Customer/Customer.xml
 * except me.jclagache.data.mybatis.repository.CustomerRepository#findByLastName(java.lang.String) query
 */
public interface CustomerRepository extends MyBatisRepository<Customer, Integer> {

	List<Customer> findByFirstName(String firstName);

	/**
	 *
	 * org.apache.ibatis.annotations.Select query annotation can be used
	 */
	@Select("SELECT customer.id id, customer.first_name first_name, customer.last_name last_name, customer.email_address email_adress, address.id address_id, " +
			"address.street address_street, address.city address_city, address.country address_country FROM customer LEFT JOIN address ON customer.id = address.customer_id WHERE " +
			" customer.last_name = #{lastName}")
	@ResultMap("customerResultMap")
	List<Customer> findByLastName(@Param("lastName")String lastName);

}
