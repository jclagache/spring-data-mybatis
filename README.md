# Spring Data MyBatis #

The primary goal of the [Spring Data](http://www.springsource.org/spring-data) project is to make it easier to build Spring-powered applications that use data access technologies. This module deals with enhanced support for [MyBatis](https://code.google.com/p/mybatis/) based data access layers.

## Features ##
This project defines a `MyBatisRepository` base interface  : 

```java
public interface MyBatisRepository<T, ID extends Serializable> extends Repository<T, ID> {	
	T findOne(ID id);
	List<T> findAll();
	boolean exists(ID id);
	long count();
}
```
The only goal for now of this module is to make your MyBatis mappers created by [MyBatis-Spring](http://mybatis.github.io/spring/) : 
 * implement these four methods by only providing one `select` statement
 * registered as Spring Data repositories


## Quick Start ##

Build and deploy it into your local maven repository :

```bash
git clone https://github.com/interair/spring-data-mybatis.git
cd spring-data-mybatis
mvn install
```

Add the jar to your maven project : 

```xml
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-mybatis</artifactId>
  <version>0.1-SNAPSHOT</version>
</dependency>
```

Configure your infrastructure: 
 1. add @EnableMyBatisRepositories annotation:
```java
@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
public class ApplicationConfig {

}
```
 2. Configure datasource:
 
```properties

spring.datasource.url=jdbc:mysql://mysql:3306/opentsp_user
spring.datasource.username=admin
spring.datasource.password=opentsp
spring.datasource.test-on-borrow=true
spring.datasource.test-while-idle=true
spring.datasource.validation-query=SELECT 1;
spring.datasource.driverClassName=com.mysql.jdbc.Driver

#package for scanning mappers see https://mybatis.github.io/spring/mappers.html#scan default value *
mybatis.mapper.base.package=org.springframework.data.mybatis.repository
#package mybatis aliases see https://mybatis.github.io/mybatis-3/configuration.html#typeAliases default value ""
mybatis.aliases.package=org.springframework.data.mybatis.domain

```

Create an entity:

```java
public class User {

	private Integer id;
	private String firstname;
	private String lastname;
	// Getters and setters
}
```

Create a repository interface:

```java
public interface CustomerRepository extends MyBatisRepository<Customer, Integer> {
}
```

Write your mapper : 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.acme.CustomerRepository">
	<resultMap id="customerResultMap" type="Customer">
		<id property="id" column="id" />
		<result property="firstName" column="first_name" />
		<result property="lastName" column="last_name" />
	</resultMap>
	<select id="find" resultMap="customerResultMap">
		SELECT customer.id id,
		customer.first_name first_name,
		customer.last_name last_name
		FROM
		customer customer	
		<if test="pk">
			WHERE customer.id = #{pk.value}
		</if>
	</select>
</mapper>
```

The `select` statement id must be named "find".
The parameter type of the `select` statement is a `map`.
This `map` has a key `pk` whose value is the object identifying this domain object.

Write a test client

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class UserRepositoryIntegrationTest {
     
  @Autowired UserRepository repository;
     
  @Test
  public void sampleTestCase() {         
	Customer customer = customerRepository.findOne(100);
	assertNotNull(customer); 
	List<Customer> customers = customerRepository.findAll();
	assertNotNull(customers);
	assertTrue(customers.size() > 0);
  }
}
```

Transaction management

see: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/transaction.html#transaction-declarative-attransactional-settings  

See the test classes for more.






