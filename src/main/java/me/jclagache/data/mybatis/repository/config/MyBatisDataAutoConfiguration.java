package me.jclagache.data.mybatis.repository.config;

import me.jclagache.data.mybatis.repository.core.mapping.SimpleMyBatisMappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisDataAutoConfiguration {

    @Bean
 	public SimpleMyBatisMappingContext myBatisMappingContext() {
        	return new SimpleMyBatisMappingContext();
    }
}
