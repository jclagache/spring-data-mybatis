package org.springframework.data.mybatis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mybatis.repository.config.EnableMyBatisRepositories;

@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
public class ApplicationConfig {

}
