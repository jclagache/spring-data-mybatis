package org.springframework.data.mybatis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mybatis.repository.config.EnableMyBatisRepositories;
import org.springframework.data.mybatis.repository.config.InfrastructureConfig;

@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
@Import(InfrastructureConfig.class)
public class ApplicationConfig {

}
