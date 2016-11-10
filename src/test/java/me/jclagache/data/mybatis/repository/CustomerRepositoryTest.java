package me.jclagache.data.mybatis.repository;

import me.jclagache.data.mybatis.ApplicationConfig;
import me.jclagache.data.mybatis.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ApplicationConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
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
