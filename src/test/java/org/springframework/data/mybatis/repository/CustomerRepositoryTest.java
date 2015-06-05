package org.springframework.data.mybatis.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mybatis.ApplicationConfig;
import org.springframework.data.mybatis.domain.Customer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CustomerRepositoryTest {

	@Autowired CustomerRepository customerRepository;
	
	@Test
	public void testFindOneCustomer() {
		Customer customer = customerRepository.findOne(100);
		assertNotNull(customer);
	}
	
	@Test
	public void testFindAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		assertNotNull(customers);
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void testFindCustomersByFirstName() {
		List<Customer> customers = customerRepository.findByFirstName("John");
		assertNotNull(customers);
		assertTrue(customers.size() == 1);
	}
	
	@Test
	public void testFindCustomersByLastName() {
		List<Customer> customers = customerRepository.findByLastName("Doe");
		assertNotNull(customers);
		assertTrue(customers.size() == 3);
	}
}
