package me.jclagache.data.mybatis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import me.jclagache.data.mybatis.repository.config.EnableMyBatisRepositories;

@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
public class ApplicationConfig {

}
