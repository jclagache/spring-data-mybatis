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

Configure your infrastructure : 

```java
@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
public class ApplicationConfig {

}
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

Create a repository interface in `com.acme.repository`:

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
	Customer customer = customerRepository.findOne(new Integer(100));
	assertNotNull(customer); 
	List<Customer> customers = customerRepository.findAll();
	assertNotNull(customers);
	assertTrue(customers.size() > 0);
  }
}
```

See the test classes for more.






