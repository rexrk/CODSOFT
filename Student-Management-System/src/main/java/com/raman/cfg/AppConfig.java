package com.raman.cfg;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = {"com.raman.dao", "com.raman.entity"})
@Configuration
public class AppConfig {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.pass}")
    private String password;


    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(this.driverClassName);
        bds.setUrl(this.url);
        bds.setUsername(this.user);
        bds.setPassword(this.password);

        bds.setInitialSize(2);
        bds.setMaxTotal(10);
        bds.setMinIdle(2);
        bds.setMaxIdle(5);
        bds.setMaxWaitMillis(500);

        return bds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
