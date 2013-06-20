package org.springframework.data.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mybatis.repository.config.EnableMyBatisRepositories;

@Configuration
@EnableMyBatisRepositories
@Import(InfrastructureConfig.class)
public class ApplicationConfig {

}
