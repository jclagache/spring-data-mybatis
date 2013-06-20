# Spring Data MyBatis #

The primary goal of the [Spring Data](http://www.springsource.org/spring-data) project is to make it easier to build Spring-powered applications that use data access technologies. This module deals with enhanced support for [MyBatis](https://code.google.com/p/mybatis/) based data access layers.

## Features ##
This project define a `MyBatisRepository` base interface  : 

```java
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
```
The only goal for now of this module is to make your MyBatis mappers created by [MyBatis-Spring](http://mybatis.github.io/spring/) : 
 * potentially expose this 2 methods by default
 * registered as Spring Data repositories


## Quick Start ##

Build and deploy it into your local maven repository :

```bash
git clone https://github.com/jclagache/spring-data-mybatis.git
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
@MapperScan("com.acme..repository")
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addDefaultScripts().build();
		return db;
	}	

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.acme.domain");
		return sessionFactory.getObject();
	}

	@Bean
	SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
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

Create a repository interface in `com.acme..repository`:

```java
public interface CustomerRepository extends MyBatisRepository<Customer, Integer> {
}
```

Write your mapper : 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.springframework.data.mybatis.repository.CustomerRepository">
	<resultMap id="customerResultMap" type="Customer">
		<id property="id" column="id" />
		<result property="firstName" column="first_name" />
		<result property="lastName" column="last_name" />
	</resultMap>
	<select id="findOne" resultMap="customerResultMap" parameterType="int">
		SELECT customer.id id,
		customer.first_name first_name,
		customer.last_name last_name
		FROM customer customer
		WHERE customer.id = #{id}
	</select>	
	<select id="findAll" resultMap="customerResultMap">
		SELECT customer.id id,
		customer.first_name first_name,
		customer.last_name last_name
		FROM customer customer		
	</select>
</mapper>
```

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
  }
}
```






