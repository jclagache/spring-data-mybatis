package me.jclagache.data.mybatis;

import me.jclagache.data.mybatis.repository.config.EnableMyBatisRepositories;
import me.jclagache.data.mybatis.repository.config.MyBatisAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMyBatisRepositories
@EnableAutoConfiguration
public class ApplicationConfig extends MyBatisAutoConfiguration {

}
